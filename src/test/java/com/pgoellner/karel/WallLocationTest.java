package com.pgoellner.karel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

class WallLocationTest {

    @ParameterizedTest
    @MethodSource
    void generates_opposite_WallLocation(Location<Orientation> original, Location<Orientation> expectedResult) {
        Assertions.assertEquals(expectedResult, new World(0, 0, new ArrayList<>(), new ArrayList<>()).oppositeWallLocation(original));
    }

    private static Stream<Arguments> generates_opposite_WallLocation() {
        return Stream.of(
                Arguments.of(
                        new Location<>(new Coordinates(1, 1), Orientation.EAST),
                        new Location<>(new Coordinates(2, 1), Orientation.WEST)
                ),
                Arguments.of(
                        new Location<>(new Coordinates(2, 1), Orientation.WEST),
                        new Location<>(new Coordinates(1, 1), Orientation.EAST)
                ),
                Arguments.of(
                        new Location<>(new Coordinates(1, 1), Orientation.NORTH),
                        new Location<>(new Coordinates(1, 2), Orientation.SOUTH)
                ),
                Arguments.of(
                        new Location<>(new Coordinates(1, 2), Orientation.SOUTH),
                        new Location<>(new Coordinates(1, 1), Orientation.NORTH)
                )
        );
    }
}