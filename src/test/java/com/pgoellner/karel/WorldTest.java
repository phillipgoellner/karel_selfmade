package com.pgoellner.karel;

import com.pgoellner.karel.geometry.Coordinates;
import com.pgoellner.karel.geometry.Location;
import com.pgoellner.karel.geometry.Orientation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class WorldTest {
    private final World world = new World(
            5,
            5,
            Stream.of(
                    new Location<>(new Coordinates(2, 3), Orientation.EAST),
                    new Location<>(new Coordinates(4, 4), Orientation.SOUTH)
            ).collect(Collectors.toList()),
            new ArrayList<>()
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

}