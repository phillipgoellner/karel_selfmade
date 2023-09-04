package com.pgoellner.karel;

import java.util.ArrayList;
import java.util.List;

public final class World {
    private final int[][] beeperPlacements;
    private final List<WallLocation> walls;

    World(int x, int y) {
        this.beeperPlacements = new int[x][y];
        this.beeperPlacements[0][0]++;

        walls = new ArrayList<>();
        walls.add(new WallLocation(new Coordinates(1, 8), Orientation.SOUTH));
        walls.add(new WallLocation(new Coordinates(7, 8), Orientation.SOUTH));
        walls.add(new WallLocation(new Coordinates(8, 1), Orientation.WEST));
        walls.add(new WallLocation(new Coordinates(8, 7), Orientation.WEST));
        walls.add(new WallLocation(new Coordinates(8, 8), Orientation.WEST));
        walls.add(new WallLocation(new Coordinates(8, 8), Orientation.SOUTH));
        walls.add(new WallLocation(new Coordinates(8, 14), Orientation.WEST));
        walls.add(new WallLocation(new Coordinates(14, 8), Orientation.SOUTH));
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
