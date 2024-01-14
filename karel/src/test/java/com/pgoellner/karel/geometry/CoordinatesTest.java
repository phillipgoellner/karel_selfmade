package com.pgoellner.karel.geometry;

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

    @ParameterizedTest
    @MethodSource
    void coordinates_sum_correctly(Coordinates firstSummend, Coordinates secondSummend, Coordinates expectedResult) {
        Coordinates calculated = firstSummend.plus(secondSummend);

        Assertions.assertEquals(expectedResult, calculated);
    }

    private static Stream<Arguments> coordinates_sum_correctly() {
        return Stream.of(
                Arguments.of(new Coordinates(1, 1), new Coordinates(1, 1), new Coordinates(2, 2)),
                Arguments.of(new Coordinates(1, 9), new Coordinates(1, 1), new Coordinates(2, 10))
        );
    }

    @ParameterizedTest
    @MethodSource
    void coordinates_subtract_correctly(Coordinates subtrahend, Coordinates minuend, Coordinates expectedResult) {
        Coordinates calculated = subtrahend.minus(minuend);

        Assertions.assertEquals(expectedResult, calculated);
    }

    private static Stream<Arguments> coordinates_subtract_correctly() {
        return Stream.of(
                Arguments.of(new Coordinates(1, 1), new Coordinates(1, 1), new Coordinates(0, 0)),
                Arguments.of(new Coordinates(1, 9), new Coordinates(1, 1), new Coordinates(0, 8))
        );
    }
}
