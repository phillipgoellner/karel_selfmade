package com.pgoellner.karel;

import java.util.ArrayList;
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
}

class WallLocation {
    final Coordinates coordinates;
    final Orientation orientation;

    WallLocation(Coordinates coordinates, Orientation orientation) {
        this.coordinates = coordinates;
        this.orientation = orientation;
    }
}
