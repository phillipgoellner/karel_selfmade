package com.pgoellner.karel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public final class World {
    private final int[][] beeperPlacements;
    private final List<WallLocation> walls;
    private final List<ColourLocation> colours;

    World(int x, int y, List<WallLocation> walls, List<Coordinates> beepers) {
        this(x, y, walls, beepers, new ArrayList<>());
    }

    World(int x, int y, List<WallLocation> walls, List<Coordinates> beepers, List<ColourLocation> colours) {
        this.beeperPlacements = new int[x][y];

        this.walls = walls;
        this.colours = colours;
        for (Coordinates beeperLocation : beepers) {
            this.beeperPlacements[beeperLocation.x][beeperLocation.y]++;
        }
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

    void removeBeeper(Coordinates location) {
        beeperPlacements[location.x][location.y] -= 1;
    }

    int numberOfBeepersAt(Coordinates location) {
        return beeperPlacements[location.x][location.y];
    }

    List<WallLocation> allWalls() {
        return new ArrayList<>(walls);
    }

    List<ColourLocation> allColours() {
        return new ArrayList<>(colours);
    }

    boolean viewIsBlocked(Coordinates position, Orientation viewingDirection) {
        return watchingAtWall(position, viewingDirection) || watchingAtBorder(position, viewingDirection);
    }

    private boolean watchingAtWall(Coordinates position, Orientation viewingDirection) {
        final WallLocation positionToCheck = new WallLocation(position, viewingDirection);

        for (WallLocation location : walls) {
            if (location.equals(positionToCheck) ||
                            location.oppositeWallLocation().equals(positionToCheck)) {
                return true;
            }
        }
        return false;
    }

    private boolean watchingAtBorder(Coordinates position, Orientation viewingDirection) {
        switch (viewingDirection) {
            case NORTH:
                return position.y == yDimension();
            case EAST:
                return position.x == xDimension();
            case SOUTH:
                return position.y == 1;
            case WEST:
                return position.x == 1;
            default:
                return false;
        }
    }


    public String toString() {
        return String.format("Dim: (%s/%s) / Beepers: %s / Walls: %s",
                xDimension(),
                yDimension(),
                beeperPlacements,
                walls);
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean equals(Object other) {
        if (other instanceof World) {
            World otherWorld = (World) other;
            return new HashSet<>(otherWorld.walls).containsAll(this.walls) &&
                    Arrays.deepEquals(otherWorld.beeperPlacements, this.beeperPlacements);
        }
        return false;
    }
}

class WallLocation {
    final Coordinates coordinates;
    final Orientation orientation;

    WallLocation(Coordinates coordinates, Orientation orientation) {
        this.coordinates = coordinates;
        this.orientation = orientation;
    }

    WallLocation oppositeWallLocation() {
        final Orientation flippedOrientation = Orientation.flip(orientation);
        final Coordinates flippedCoordinates;

        switch (orientation) {
            case NORTH:
                flippedCoordinates = new Coordinates(coordinates.x, coordinates.y + 1);
                break;
            case WEST:
                flippedCoordinates = new Coordinates(coordinates.x - 1, coordinates.y);
                break;
            case SOUTH:
                flippedCoordinates = new Coordinates(coordinates.x, coordinates.y - 1);
                break;
            case EAST:
                flippedCoordinates = new Coordinates(coordinates.x + 1, coordinates.y);
                break;
            default:
                flippedCoordinates = coordinates;
        }

        return new WallLocation(flippedCoordinates, flippedOrientation);
    }

    public String toString() {
        return String.format("(%d/%d) - %s", coordinates.x, coordinates.y, orientation);
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean equals(Object other) {
        return (other instanceof WallLocation) && (hashCode() == other.hashCode());
    }
}

class ColourLocation {
    final Coordinates location;
    final Color colour;

    ColourLocation(Coordinates location, Color colour) {
        this.location = location;
        this.colour = colour;
    }

    public static Color colourFrom(String string) {
        switch (string.toLowerCase()) {
            case "gray": return Color.GRAY;
            case "red": return Color.RED;
            case "blue": return Color.BLUE;
            default: return Color.WHITE;
        }
    }
}
