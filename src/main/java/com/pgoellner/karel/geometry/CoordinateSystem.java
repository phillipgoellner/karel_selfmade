package com.pgoellner.karel.geometry;

public class CoordinateSystem {
    private final Coordinates origin;
    private final Coordinates furthestCorner;

    public CoordinateSystem(Coordinates origin, Coordinates furthestCorner) {
        this.origin = origin;
        this.furthestCorner = furthestCorner;
    }

    public int xLimit() {
        return furthestCorner.x;
    }

    public int yLimit() {
        return furthestCorner.y;
    }
}
