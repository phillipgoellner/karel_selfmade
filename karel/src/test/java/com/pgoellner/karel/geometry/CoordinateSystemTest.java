package com.pgoellner.karel.geometry;

import com.pgoellner.karel.ArgumentList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

class CoordinateSystemTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 360, -360})
    void no_effective_rotation(int degrees) {
        List<Coordinates> givenCoordinates = ArgumentList.of(
                new Coordinates(1, 2),
                new Coordinates(1, 1),
                new Coordinates(2, 2)
        );

        List<Coordinates> rotatedCoordinates = CoordinateSystem.rotate(
                new Coordinates(2, 2),
                givenCoordinates,
                degrees
        );

        List<Coordinates> expectedCoordinates = ArgumentList.of(
                new Coordinates(1, 2),
                new Coordinates(1, 1),
                new Coordinates(2, 2)
        );

        Assertions.assertEquals(
                expectedCoordinates,
                rotatedCoordinates
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {90, -270})
    void rotate_effectively_90_degrees(int degrees) {
        List<Coordinates> givenCoordinates = ArgumentList.of(
                new Coordinates(1, 2),
                new Coordinates(1, 1),
                new Coordinates(2, 2)
        );

        List<Coordinates> rotatedCoordinates = CoordinateSystem.rotate(
                new Coordinates(2, 2),
                givenCoordinates,
                degrees
        );

        List<Coordinates> expectedCoordinates = ArgumentList.of(
                new Coordinates(2, 1),
                new Coordinates(3, 1),
                new Coordinates(2, 2)
        );

        Assertions.assertEquals(
                expectedCoordinates,
                rotatedCoordinates
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {180, -180})
    void rotate_effectively_180_degrees(int degrees) {
        List<Coordinates> givenCoordinates = ArgumentList.of(
                new Coordinates(1, 2),
                new Coordinates(1, 1),
                new Coordinates(2, 2)
        );

        List<Coordinates> rotatedCoordinates = CoordinateSystem.rotate(
                new Coordinates(2, 2),
                givenCoordinates,
                degrees
        );

        List<Coordinates> expectedCoordinates = ArgumentList.of(
                new Coordinates(3, 2),
                new Coordinates(3, 3),
                new Coordinates(2, 2)
        );

        Assertions.assertEquals(
                expectedCoordinates,
                rotatedCoordinates
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {270, -90})
    void rotate_270_degrees(int degrees) {
        List<Coordinates> givenCoordinates = ArgumentList.of(
                new Coordinates(1, 2),
                new Coordinates(1, 1),
                new Coordinates(2, 2)
        );

        List<Coordinates> rotatedCoordinates = CoordinateSystem.rotate(
                new Coordinates(2, 2),
                givenCoordinates,
                degrees
        );

        List<Coordinates> expectedCoordinates = ArgumentList.of(
                new Coordinates(2, 3),
                new Coordinates(1, 3),
                new Coordinates(2, 2)
        );

        Assertions.assertEquals(
                expectedCoordinates,
                rotatedCoordinates
        );
    }
}
