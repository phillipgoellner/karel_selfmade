package com.pgoellner.karel;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorldFileParser {
    private final List<String> lines;

    public WorldFileParser() {
        lines = Stream.of(
                "Dimension: (14, 14)",
                "Wall: (1, 8) south",
                "Wall: (7, 8) south",
                "Wall: (8, 1) west",
                "Wall: (8, 7) west",
                "Wall: (8, 8) west",
                "Wall: (8, 8) south",
                "Wall: (8, 14) west",
                "Wall: (14, 8) south",
                "Beeper: (1, 1) 1",
                "Beeper: (1, 7) 1",
                "Beeper: (1, 8) 1",
                "Beeper: (1, 14) 1",
                "Beeper: (4, 4) 1",
                "Beeper: (4, 11) 1",
                "Beeper: (7, 1) 1",
                "Beeper: (7, 7) 1",
                "Beeper: (7, 8) 1",
                "Beeper: (7, 14) 1",
                "Beeper: (8, 1) 1",
                "Beeper: (8, 7) 1",
                "Beeper: (8, 8) 1",
                "Beeper: (8, 14) 1",
                "Beeper: (11, 4) 1",
                "Beeper: (11, 11) 1",
                "Beeper: (14, 1) 1",
                "Beeper: (14, 7) 1",
                "Beeper: (14, 8) 1",
                "Beeper: (14, 14) 1",
                "Karel: (4, 8) east",
                "",
                "BeeperBag: INFINITE",
                "Speed: 0.50"
        ).collect(Collectors.toList());
    }

    public World fromDescription() {
        return new World(14, 14, walls(), beeperLocations());
    }

    private List<Coordinates> beeperLocations() {
        return this.lines
                .stream()
                .filter(line -> line.contains("Beeper: ("))
                .map(line -> {
                    final String beeperCoordinates = line.replace("Beeper: (", "").split("\\)")[0];
                    final String x = beeperCoordinates.split(", ")[0];
                    final String y = beeperCoordinates.split(", ")[1];

                    return new Coordinates(Integer.parseInt(x) - 1, Integer.parseInt(y) - 1);
                })
                .collect(Collectors.toList());
    }

    private List<WallLocation> walls() {
        return this.lines.stream()
                .filter(line -> line.contains("Wall: ("))
                .map(line -> {
                    final String wallCoordinates = line.replace("Wall: (", "").split("\\)")[0];
                    final String x = wallCoordinates.split(", ")[0];
                    final String y = wallCoordinates.split(", ")[1];

                    return new WallLocation(new Coordinates(Integer.parseInt(x), Integer.parseInt(y)), Orientation.from(line.split("\\) ")[1]));
                })
                .collect(Collectors.toList());
    }
}
