package com.pgoellner.karel.parse;

import com.pgoellner.karel.geometry.Coordinates;
import com.pgoellner.karel.geometry.Location;
import com.pgoellner.karel.geometry.Orientation;
import com.pgoellner.karel.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorldFileParser {
    private final List<String> lines;

    private static final String karelLineHeading = "Karel: (";
    private static final String beeperLineHeading = "Beeper: (";
    private static final String wallLineHeading = "Wall: (";
    private static final String colorLineHeading = "Color: (";
    private static final String dimensionLineHeading = "Dimension: (";

    public WorldFileParser() {
        this(MadLabyrinth.PARSE_INPUT.collect(Collectors.toList()));
    }

    public WorldFileParser(List<String> lines) {
        this.lines = new ArrayList<>(lines);
    }

    public World fromDescription() {
        final Coordinates farCorner = worldFarCorner();
        return new World(farCorner.x, farCorner.y, walls(), beeperLocations(), colours());
    }

    public Coordinates karelStartingPoint() {
        return this.lines
                .stream()
                .filter(line -> line.contains(karelLineHeading))
                .map(line -> coordinatesFrom(line, karelLineHeading))
                .findFirst()
                .orElse(new Coordinates(0, 0));
    }

    public Orientation karelStartingOrientation() {
        return this.lines
                .stream()
                .filter(line -> line.contains(karelLineHeading))
                .map(line -> {
                    final String karelOrientation = line.split("\\) ")[1];

                    return Orientation.from(karelOrientation);
                })
                .findFirst()
                .orElse(Orientation.EAST);
    }

    private List<Coordinates> beeperLocations() {
        return this.lines
                .stream()
                .filter(line -> line.contains(beeperLineHeading))
                .map(line -> {
                    final int beeperCount = Integer.parseInt(line.split("\\) ")[1]);

                    return new Location<>(coordinatesFrom(line, beeperLineHeading).minus(Coordinates.UNIT), beeperCount);
                })
                .flatMap(location -> {
                    final List<Coordinates> beepersOnLocation = new ArrayList<>();
                    for (int i = 0; i < location.content; i++) {
                        beepersOnLocation.add(location.coordinates);
                    }
                    return beepersOnLocation.stream();
                })
                .collect(Collectors.toList());
    }

    private List<Location<Orientation>> walls() {
        return this.lines.stream()
                .filter(line -> line.contains(wallLineHeading))
                .map(line -> new Location<>(
                        coordinatesFrom(line, wallLineHeading),
                        Orientation.from(line.split("\\) ")[1])
                ))
                .collect(Collectors.toList());
    }

    private List<Location<Color>> colours() {
        return this.lines.stream()
                .filter(line -> line.contains(colorLineHeading))
                .map(line -> {
                    Color result;
                    switch (line.split("\\) ")[1].toLowerCase()) {
                        case "gray":
                            result = Color.GRAY;
                            break;
                        case "red":
                            result = Color.RED;
                            break;
                        case "blue":
                            result = Color.BLUE;
                            break;
                        default:
                            result = Color.WHITE;
                            break;
                    }
                    return new Location<>(coordinatesFrom(line, colorLineHeading), result);
                })
                .collect(Collectors.toList());
    }

    private Coordinates worldFarCorner() {
        return this.lines
                .stream()
                .filter(line -> line.contains(dimensionLineHeading))
                .map(line -> coordinatesFrom(line, dimensionLineHeading))
                .findFirst()
                .orElse(new Coordinates(5, 5));
    }

    private static Coordinates coordinatesFrom(String line, String lineHeading) {
            final String karelCoordinate = line.replace(lineHeading, "").split("\\)")[0];
            final String x = karelCoordinate.split(", ")[0];
            final String y = karelCoordinate.split(", ")[1];

            return new Coordinates(Integer.parseInt(x), Integer.parseInt(y));
    }
}
