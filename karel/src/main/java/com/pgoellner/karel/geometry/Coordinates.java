package com.pgoellner.karel.geometry;

public class Coordinates {
    public static final Coordinates UNIT = new Coordinates(1, 1);
    public final int x;
    public final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates mirrorOnY(int maxY) {
        int translatedY = maxY - y + 1;
        return new Coordinates(x, translatedY);
    }

    public Coordinates plus(Coordinates summend) {
        return new Coordinates(x + summend.x, y + summend.y);
    }

    public Coordinates minus(Coordinates minuend) {
        return new Coordinates(x - minuend.x, y - minuend.y);
    }

    public String toString() {
        return String.format("(%d/%d)", x, y);
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean equals(Object other) {
        return (other instanceof Coordinates) && (hashCode() == other.hashCode());
    }
}
