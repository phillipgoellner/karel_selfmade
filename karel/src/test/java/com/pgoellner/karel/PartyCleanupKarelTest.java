package com.pgoellner.karel;

import com.pgoellner.karel.geometry.Coordinates;
import com.pgoellner.karel.geometry.Orientation;
import com.pgoellner.karel.parse.WorldFileParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

class PartyCleanupKarelTest {
    @Test
    void works() {
        WorldFileParser parser = new WorldFileParser(ArgumentList.of(
                "Dimension: (6, 6)",
                "Wall: (4, 1) west",
                "Wall: (4, 2) south",
                "Wall: (5, 2) south",
                "Wall: (6, 2) south",
                "Beeper: (2, 1) 1",
                "Karel: (1, 1) east",
                "BeeperBag: INFINITE",
                "Speed: 0.50"
        ));

        World world = parser.fromDescription();

        Karel testProgram = new TestKarel(
                world,
                parser.karelStartingPoint(),
                parser.karelStartingOrientation()
        );

        testProgram.run();
        Assertions.assertAll(
                () -> Assertions.assertEquals(new HashSet<>(ArgumentList.of(new Coordinates(6, 2))), world.beeperLocations()),
                () -> Assertions.assertEquals(new Coordinates(6, 2), testProgram.position())
        );
    }
    private static class TestKarel extends Karel {
        TestKarel(World world, Coordinates startingPoint, Orientation startingOrientation) {
            super(world, startingPoint, startingOrientation);
        }

        public void run() {
            move();
            pickBeeper();
            move();
            turnLeft();
            move();
            turnLeft();
            turnLeft();
            turnLeft();
            move();
            move();
            move();
            putBeeper();
        }
    }
}
