package com.pgoellner.karel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CoordinatesTest {

    @ParameterizedTest
    @MethodSource
    void coordinates_mirrors_correctly(Coordinates original, int maxY, Coordinates expected) {
        Coordinates mirrored = original.mirrorOnY(maxY);

        Assertions.assertEquals(expected, mirrored);
    }

    private static Stream<Arguments> coordinates_mirrors_correctly() {
        return Stream.of(
                Arguments.of(new Coordinates(1, 1), 9, new Coordinates(1, 9)),
                Arguments.of(new Coordinates(1, 9), 9, new Coordinates(1, 1))
        );
    }
}
