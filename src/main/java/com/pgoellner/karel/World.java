package com.pgoellner.karel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public final class World {
    private final int[][] beeperPlacements;
    private final List<WallLocation> walls;

    World(int x, int y, List<WallLocation> walls, List<Coordinates> beepers) {
        this.beeperPlacements = new int[x][y];

        this.walls = walls;
        for (Coordinates beeperLocation : beepers) {
            this.beeperPlacements[beeperLocation.x][beeperLocation.y]++;
        }
    }

    int xDimension() {
        return beeperPlacements.length;
    }

    int yDimension() {
        return beeperPlacements[0].length;
    }

    void putBeeper(Coordinates location) {
        beeperPlacements[location.x][location.y] += 1;
    }

    int numberOfBeepersAt(Coordinates location) {
        return beeperPlacements[location.x][location.y];
    }

    List<WallLocation> allWalls() {
        return new ArrayList<>(walls);
    }


    public String toString() {
        return String.format("Dim: (%s/%s) / Beepers: %s / Walls: %s",
                xDimension(),
                yDimension(),
                beeperPlacements,
                walls);
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean equals(Object other) {
        if (other instanceof World) {
            World otherWorld = (World) other;
            return new HashSet<>(otherWorld.walls).containsAll(this.walls) &&
                    Arrays.deepEquals(otherWorld.beeperPlacements, this.beeperPlacements);
        }
        return false;
    }
}

class WallLocation {
    final Coordinates coordinates;
    final Orientation orientation;

    WallLocation(Coordinates coordinates, Orientation orientation) {
        this.coordinates = coordinates;
        this.orientation = orientation;
    }
    public String toString() {
        return String.format("(%d/%d) - %s", coordinates.x, coordinates.y, orientation);
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean equals(Object other) {
        return (other instanceof WallLocation) && (hashCode() == other.hashCode());
    }
}
