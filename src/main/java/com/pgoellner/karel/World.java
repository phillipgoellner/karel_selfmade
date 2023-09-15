package com.pgoellner.karel;

import com.pgoellner.karel.geometry.CoordinateSystem;
import com.pgoellner.karel.geometry.Coordinates;
import com.pgoellner.karel.geometry.Location;
import com.pgoellner.karel.geometry.Orientation;

import java.awt.*;
import java.util.*;
import java.util.List;

public final class World {
    private final CoordinateSystem expanse;

    private final List<Location<Color>> colours;
    private final Map<Coordinates, Integer> beepers;
    private final List<Location<Orientation>> walls;

    World(int x, int y) {
        this(x, y, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    World(int x, int y, List<Location<Orientation>> walls, List<Coordinates> beepers) {
        this(x, y, walls, beepers, new ArrayList<>());
    }

    public World(int x, int y, List<Location<Orientation>> walls, List<Coordinates> beepers, List<Location<Color>> colours) {
        expanse = new CoordinateSystem(new Coordinates(1, 1), new Coordinates(x, y));

        this.walls = walls;
        this.beepers = new HashMap<>();
        this.colours = colours;
        for (Coordinates beeperLocation : beepers) {
            this.beepers.put(beeperLocation, 1 + this.beepers.getOrDefault(beeperLocation, 0));
        }
    }

    int xDimension() {
        return expanse.xLimit();
    }

    int yDimension() {
        return expanse.yLimit();
    }

    void putBeeper(Coordinates location) {
        this.beepers.put(location, 1 + this.beepers.getOrDefault(location, 0));
    }

    void removeBeeper(Coordinates location) {
        int currentNumberOfBeepers = this.beepers.getOrDefault(location, 0);

        if (currentNumberOfBeepers > 0) {
            this.beepers.put(location, currentNumberOfBeepers - 1);
        }
    }

    int numberOfBeepersAt(Coordinates location) {
        return beepers.getOrDefault(location, 0);
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
                beepers,
                walls);
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean equals(Object other) {
        if (other instanceof World) {
            World otherWorld = (World) other;
            return new HashSet<>(otherWorld.walls).containsAll(this.walls) &&
                    beepers.keySet().containsAll(otherWorld.beepers.keySet());
        }
        return false;
    }
}
