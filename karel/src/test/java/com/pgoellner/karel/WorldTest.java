package com.pgoellner.karel;

import com.pgoellner.karel.errors.NoBeeperPresent;
import com.pgoellner.karel.geometry.Coordinates;
import com.pgoellner.karel.geometry.Location;
import com.pgoellner.karel.geometry.Orientation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class WorldTest {
    private final World world = new World(
            5,
            5,
            ArgumentList.of(
                    new Location<>(new Coordinates(2, 3), Orientation.EAST),
                    new Location<>(new Coordinates(4, 4), Orientation.SOUTH)
            ),
            ArgumentList.of()
    );

    @ParameterizedTest
    @MethodSource
    void front_of_view_point_is_blocked(Coordinates standing, Orientation viewingDirection) {
        Assertions.assertTrue(world.viewIsBlocked(standing, viewingDirection));
    }

    private static Stream<Arguments> front_of_view_point_is_blocked() {
        return Stream.of(
                Arguments.of(new Coordinates(3, 5), Orientation.NORTH),
                Arguments.of(new Coordinates(5, 1), Orientation.EAST),
                Arguments.of(new Coordinates(3, 1), Orientation.SOUTH),
                Arguments.of(new Coordinates(1, 1), Orientation.WEST),
                Arguments.of(new Coordinates(2, 3), Orientation.EAST),
                Arguments.of(new Coordinates(3, 3), Orientation.WEST),
                Arguments.of(new Coordinates(4, 3), Orientation.NORTH)
        );
    }

    @Test
    void placing_beepers_persist_them() {
        World worldToReset = new World(
                5,
                5,
                ArgumentList.of(),
                ArgumentList.of(new Coordinates(1, 1))
        );

        worldToReset.placeBeeper(new Coordinates(2, 2));
        worldToReset.placeBeeper(new Coordinates(2, 3));

        Assertions.assertEquals(new World(
                5,
                5,
                ArgumentList.of(),
                ArgumentList.of(
                        new Coordinates(1, 1),
                        new Coordinates(2, 2),
                        new Coordinates(2, 3))
        ), worldToReset);
    }

    @Test
    void resetting_the_world_removes_newly_placed_beepers() {
        World worldToReset = new World(
                5,
                5,
                ArgumentList.of(),
                ArgumentList.of(new Coordinates(1, 1))
        );

        worldToReset.placeBeeper(new Coordinates(2, 2));
        worldToReset.placeBeeper(new Coordinates(2, 3));
        worldToReset.resetToOriginalState();

        Assertions.assertEquals(new World(
                5,
                5,
                ArgumentList.of(),
                ArgumentList.of(new Coordinates(1, 1))
        ), worldToReset);
    }

    @Test
    void error_when_trying_to_remove_a_non_existing_beeper() {
        Assertions.assertThrows(NoBeeperPresent.class, () -> new World(3, 3).removeBeeper(new Coordinates(1, 1)));
    }
}