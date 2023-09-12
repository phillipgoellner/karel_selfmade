package com.pgoellner.karel;

import com.pgoellner.karel.geometry.Coordinates;
import com.pgoellner.karel.geometry.Location;
import com.pgoellner.karel.geometry.Orientation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public final class World {
    private final int[][] beeperPlacements;
    private final List<Location<Orientation>> walls;
    private final List<Location<Color>> colours;

    World(int x, int y, List<Location<Orientation>> walls, List<Coordinates> beepers) {
        this(x, y, walls, beepers, new ArrayList<>());
    }

    public World(int x, int y, List<Location<Orientation>> walls, List<Coordinates> beepers, List<Location<Color>> colours) {
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

    public List<Location<Orientation>> allWalls() {
        return new ArrayList<>(walls);
    }

    public List<Location<Color>> allColours() {
        return new ArrayList<>(colours);
    }

    boolean viewIsBlocked(Coordinates position, Orientation viewingDirection) {
        return watchingAtWall(position, viewingDirection) || watchingAtBorder(position, viewingDirection);
    }

    private boolean watchingAtWall(Coordinates position, Orientation viewingDirection) {
        final Location<Orientation> positionToCheck = new Location<>(position, viewingDirection);

        for (Location<Orientation> location : walls) {

            if (location.equals(positionToCheck) ||
                    (oppositeWallLocation(location).equals(positionToCheck))) {
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

    Location<Orientation> oppositeWallLocation(Location<Orientation> location) {
        final Orientation flippedOrientation = Orientation.flip(location.content);
        final Coordinates flippedCoordinates;

        switch (location.content) {
            case NORTH:
                flippedCoordinates = new Coordinates(location.coordinates.x, location.coordinates.y + 1);
                break;
            case WEST:
                flippedCoordinates = new Coordinates(location.coordinates.x - 1, location.coordinates.y);
                break;
            case SOUTH:
                flippedCoordinates = new Coordinates(location.coordinates.x, location.coordinates.y - 1);
                break;
            case EAST:
                flippedCoordinates = new Coordinates(location.coordinates.x + 1, location.coordinates.y);
                break;
            default:
                flippedCoordinates = location.coordinates;
        }

        return new Location<>(flippedCoordinates, flippedOrientation);
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
