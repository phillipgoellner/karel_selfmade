package com.pgoellner.karel;

public enum Orientation {
    NORTH, WEST, SOUTH, EAST;

    public static Orientation rotateLeft(Orientation original) {
        final Orientation result;

        switch (original) {
            case NORTH: result = WEST; break;
            case EAST: result = NORTH; break;
            case SOUTH: result = EAST; break;
            default: result = SOUTH; break;
        }

        return result;
    }

    public static Orientation flip(Orientation original) {
        switch (original) {
            case NORTH: return SOUTH;
            case EAST: return WEST;
            case SOUTH: return NORTH;
            default: return EAST;
        }
    }

    public static Orientation from(String orientationString) {
        switch (orientationString.toLowerCase()) {
            case "north": return NORTH;
            case "east": return EAST;
            case "south": return SOUTH;
            default: return WEST;
        }
    }
}
