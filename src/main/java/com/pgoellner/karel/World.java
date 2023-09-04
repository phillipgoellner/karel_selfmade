package com.pgoellner.karel;

public final class World {
    private final int[][] beeperPlacements;

    World(int x, int y) {
        this.beeperPlacements = new int[x][y];
        this.beeperPlacements[0][0]++;
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
}
