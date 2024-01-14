package com.pgoellner.karel.geometry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class OrientationTest {
    @ParameterizedTest
    @MethodSource
    void orientation_rotation(Orientation original, Orientation expectedResult) {
        Orientation rotated = Orientation.rotateLeft(original);

        Assertions.assertEquals(expectedResult, rotated);
    }

    private static Stream<Arguments> orientation_rotation() {
        return Stream.of(
                Arguments.of(Orientation.NORTH, Orientation.WEST),
                Arguments.of(Orientation.EAST, Orientation.NORTH),
                Arguments.of(Orientation.SOUTH, Orientation.EAST),
                Arguments.of(Orientation.WEST, Orientation.SOUTH)
        );
    }
    @ParameterizedTest
    @MethodSource
    void orientation_flip(Orientation original, Orientation expectedResult) {
        Orientation rotated = Orientation.flip(original);

        Assertions.assertEquals(expectedResult, rotated);
    }

    private static Stream<Arguments> orientation_flip() {
        return Stream.of(
                Arguments.of(Orientation.NORTH, Orientation.SOUTH),
                Arguments.of(Orientation.EAST, Orientation.WEST),
                Arguments.of(Orientation.SOUTH, Orientation.NORTH),
                Arguments.of(Orientation.WEST, Orientation.EAST)
        );
    }

    @ParameterizedTest
    @MethodSource
    void parses_strings(String orientationString, Orientation expectedOrientation) {
        Orientation rotated = Orientation.from(orientationString);

        Assertions.assertEquals(expectedOrientation, rotated);
    }

    private static Stream<Arguments> parses_strings() {
        return Stream.of(
                Arguments.of("north", Orientation.NORTH),
                Arguments.of("North", Orientation.NORTH),
                Arguments.of("east", Orientation.EAST),
                Arguments.of("East", Orientation.EAST),
                Arguments.of("south", Orientation.SOUTH),
                Arguments.of("South", Orientation.SOUTH),
                Arguments.of("west", Orientation.WEST),
                Arguments.of("West", Orientation.WEST)
        );
    }
}
