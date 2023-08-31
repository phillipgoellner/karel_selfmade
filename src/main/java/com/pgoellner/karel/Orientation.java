package com.pgoellner.karel;

enum Orientation {
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
}
