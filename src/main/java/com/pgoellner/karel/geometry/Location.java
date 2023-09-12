package com.pgoellner.karel.geometry;

public class Location<T> {
    public final Coordinates coordinates;
    public final T content;

    public Location(Coordinates coordinates, T content) {
        this.coordinates = coordinates;
        this.content = content;
    }

    public String toString() {
        return String.format("(%d/%d) - %s", coordinates.x, coordinates.y, content);
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean equals(Object other) {
        return (other instanceof Location) && (hashCode() == other.hashCode());
    }
}
