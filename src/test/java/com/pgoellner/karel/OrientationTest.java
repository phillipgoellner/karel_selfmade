package com.pgoellner.karel;

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
}
