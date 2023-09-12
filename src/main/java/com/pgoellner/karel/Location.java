package com.pgoellner.karel;

class Location<T> {
    final Coordinates coordinates;
    final T content;

    Location(Coordinates coordinates, T content) {
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
