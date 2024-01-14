package com.pgoellner.karel;

import com.pgoellner.karel.geometry.Coordinates;
import com.pgoellner.karel.geometry.Location;
import com.pgoellner.karel.geometry.Orientation;
import com.pgoellner.karel.parse.WorldFileParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

class WorldFileParserTest {

    @Nested
    class WithoutContent {
        WorldFileParser parser = new WorldFileParser(ArgumentList.of());

        @Test
        void has_default_coordinates() {
            Coordinates parsedCoordinates = parser.karelStartingPoint();

            Assertions.assertEquals(new Coordinates(1, 1), parsedCoordinates);
        }

        @Test
        void has_default_orientation() {
            Orientation parsedOrientation = parser.karelStartingOrientation();

            Assertions.assertEquals(Orientation.EAST, parsedOrientation);
        }

        @Test
        void has_empty_world() {
            World parsedWorld = parser.fromDescription();
            World expectedWorld = new World(5, 5, ArgumentList.of(), ArgumentList.of());

            Assertions.assertEquals(expectedWorld, parsedWorld);
        }
    }

    @Nested
    class WithRegularContent {
        WorldFileParser parser = new WorldFileParser(
                ArgumentList.of(
                        "Dimension: (9, 9)",
                        "Wall: (1, 8) south",
                        "Beeper: (8, 8) 1",
                        "Karel: (4, 8) north",
                        "Color: (2, 2) RED",
                        "",
                        "BeeperBag: INFINITE",
                        "Speed: 0.50"
                )
        );

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

            List<Location<Orientation>> walls = ArgumentList
                    .of(new Location<>(new Coordinates(1, 8), Orientation.SOUTH));

            List<Coordinates> beepers = ArgumentList
                    .of(new Coordinates(8, 8));

            World expectedWorld = new World(9, 9, walls, beepers);

            Assertions.assertEquals(expectedWorld, parsedWorld);
        }

        @Test
        void parses_world_data_with_colours() {
            World parsedWorld = parser.fromDescription();

            List<Location<Orientation>> walls = ArgumentList
                    .of(new Location<>(new Coordinates(1, 8), Orientation.SOUTH));

            List<Coordinates> beepers = ArgumentList
                    .of(new Coordinates(8, 8));

            List<Location<Color>> colours = ArgumentList
                    .of(new Location<>(new Coordinates(2, 2), Color.RED));

            World expectedWorld = new World(9, 9, walls, beepers, colours);

            Assertions.assertEquals(expectedWorld, parsedWorld);
        }
    }
}