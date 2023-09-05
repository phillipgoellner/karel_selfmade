package com.pgoellner.karel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class WallLocationTest {

    @ParameterizedTest
    @MethodSource
    void generates_opposite_WallLocation(WallLocation original, WallLocation expectedResult) {
        Assertions.assertEquals(expectedResult, original.oppositeWallLocation());
    }

    private static Stream<Arguments> generates_opposite_WallLocation() {
        return Stream.of(
                Arguments.of(
                        new WallLocation(new Coordinates(1, 1), Orientation.EAST),
                        new WallLocation(new Coordinates(2, 1), Orientation.WEST)
                ),
                Arguments.of(
                        new WallLocation(new Coordinates(2, 1), Orientation.WEST),
                        new WallLocation(new Coordinates(1, 1), Orientation.EAST)
                ),
                Arguments.of(
                        new WallLocation(new Coordinates(1, 1), Orientation.NORTH),
                        new WallLocation(new Coordinates(1, 2), Orientation.SOUTH)
                ),
                Arguments.of(
                        new WallLocation(new Coordinates(1, 2), Orientation.SOUTH),
                        new WallLocation(new Coordinates(1, 1), Orientation.NORTH)
                )
        );
    }
}