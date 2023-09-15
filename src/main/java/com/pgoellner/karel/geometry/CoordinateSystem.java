package com.pgoellner.karel.geometry;

import java.util.ArrayList;
import java.util.List;

public class CoordinateSystem {
    private final Coordinates origin;
    private final Coordinates furthestCorner;

    private final List<Coordinates> allCoordinates;

    public CoordinateSystem(Coordinates origin, Coordinates furthestCorner) {
        this.origin = origin;
        this.furthestCorner = furthestCorner;

        this.allCoordinates = new ArrayList<>();

        for (int x = origin.x; x <= furthestCorner.x; x++) {
            for (int y = origin.y; y <= furthestCorner.y; y++) {
                this.allCoordinates.add(new Coordinates(x, y));
            }
        }
    }

    public int xLimit() {
        return furthestCorner.x;
    }

    public int yLimit() {
        return furthestCorner.y;
    }

    public List<Coordinates> allCoordinates() {
        return new ArrayList<>(this.allCoordinates);
    }
}
