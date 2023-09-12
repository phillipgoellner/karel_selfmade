package com.pgoellner.karel;

import com.pgoellner.karel.geometry.Coordinates;
import com.pgoellner.karel.geometry.Location;
import com.pgoellner.karel.geometry.Orientation;
import com.pgoellner.karel.parse.WorldFileParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class WorldFileParserTest {

    @Nested
    class WithoutContent {
        WorldFileParser parser = new WorldFileParser(new ArrayList<>());

        @Test
        void has_default_coordinates() {
            Coordinates parsedCoordinates = parser.karelStartingPoint();

            Assertions.assertEquals(new Coordinates(0, 0), parsedCoordinates);
        }

        @Test
        void has_default_orientation() {
            Orientation parsedOrientation = parser.karelStartingOrientation();

            Assertions.assertEquals(Orientation.EAST, parsedOrientation);
        }

        @Test
        void has_empty_world() {
            World parsedWorld = parser.fromDescription();
            World expectedWorld = new World(5, 5, new ArrayList<>(), new ArrayList<>());

            Assertions.assertEquals(expectedWorld, parsedWorld);
        }
    }

    @Nested
    class WithRegularContent {
        WorldFileParser parser = new WorldFileParser(Stream.of(
                "Dimension: (9, 9)",
                "Wall: (1, 8) south",
                "Beeper: (8, 8) 1",
                "Karel: (4, 8) north",
                "",
                "BeeperBag: INFINITE",
                "Speed: 0.50"
        ).collect(Collectors.toList()));

        @Test
        void parses_karel_starting_point() {
            Coordinates parsedCoordinates = parser.karelStartingPoint();

            Assertions.assertEquals(new Coordinates(4, 8), parsedCoordinates);
        }

        @Test
        void parses_karel_starting_orientation() {
            Orientation parsedOrientation = parser.karelStartingOrientation();

            Assertions.assertEquals(Orientation.NORTH, parsedOrientation);
        }

        @Test
        void parses_world_data() {
            World parsedWorld = parser.fromDescription();

            List<Location<Orientation>> walls = Stream
                    .of(new Location<>(new Coordinates(1, 8), Orientation.SOUTH))
                    .collect(Collectors.toList());

            List<Coordinates> beepers = Stream
                    .of(new Coordinates(7, 7))
                    .collect(Collectors.toList());

            World expectedWorld = new World(9, 9, walls, beepers);

            Assertions.assertEquals(expectedWorld, parsedWorld);
        }
    }
}