package com.pgoellner.karel.parse;

import com.pgoellner.karel.Coordinates;
import com.pgoellner.karel.Location;
import com.pgoellner.karel.Orientation;
import com.pgoellner.karel.World;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WorldFileParser {
    private final List<String> lines;

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
                .filter(line -> line.contains("Karel: ("))
                .map(line -> {
                    final String karelCoordinate = line.replace("Karel: (", "").split("\\)")[0];
                    final String x = karelCoordinate.split(", ")[0];
                    final String y = karelCoordinate.split(", ")[1];

                    return new Coordinates(Integer.parseInt(x), Integer.parseInt(y));
                })
                .findFirst()
                .orElse(new Coordinates(0, 0));
    }

    public Orientation karelStartingOrientation() {
        return this.lines
                .stream()
                .filter(line -> line.contains("Karel: ("))
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
                .filter(line -> line.contains("Beeper: ("))
                .map(line -> {
                    final String beeperCoordinates = line.replace("Beeper: (", "").split("\\)")[0];
                    final String x = beeperCoordinates.split(", ")[0];
                    final String y = beeperCoordinates.split(", ")[1];

                    final int beeperCount = Integer.parseInt(line.split("\\) ")[1]);

                    return new Location<>(new Coordinates(Integer.parseInt(x) - 1, Integer.parseInt(y) - 1), beeperCount);
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
                .filter(line -> line.contains("Wall: ("))
                .map(line -> {
                    final String wallCoordinates = line.replace("Wall: (", "").split("\\)")[0];
                    final String x = wallCoordinates.split(", ")[0];
                    final String y = wallCoordinates.split(", ")[1];

                    return new Location<>(new Coordinates(Integer.parseInt(x), Integer.parseInt(y)), Orientation.from(line.split("\\) ")[1]));
                })
                .collect(Collectors.toList());
    }

    private List<Location<Color>> colours() {
        return this.lines.stream()
                .filter(line -> line.contains("Color: ("))
                .map(line -> {
                    final String colourLocation = line.replace("Color: (", "").split("\\) ")[0];
                    final String x = colourLocation.split(", ")[0];
                    final String y = colourLocation.split(", ")[1];

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
                    return new Location<>(
                            new Coordinates(Integer.parseInt(x), Integer.parseInt(y)),
                            result
                    );
                })
                .collect(Collectors.toList());
    }

    private Coordinates worldFarCorner() {
        return this.lines
                .stream()
                .filter(line -> line.contains("Dimension: ("))
                .map(line -> {
                    final String karelCoordinate = line.replace("Dimension: (", "").split("\\)")[0];
                    final String x = karelCoordinate.split(", ")[0];
                    final String y = karelCoordinate.split(", ")[1];

                    return new Coordinates(Integer.parseInt(x), Integer.parseInt(y));
                })
                .findFirst()
                .orElse(new Coordinates(5, 5));
    }
}
