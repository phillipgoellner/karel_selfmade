package com.pgoellner.karel;

public class Coordinates {
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

    public Coordinates plusX() {
        return new Coordinates(x + 1, y);
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
