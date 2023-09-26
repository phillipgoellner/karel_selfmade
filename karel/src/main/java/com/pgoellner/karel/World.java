package com.pgoellner.karel;

import com.pgoellner.karel.geometry.CoordinateSystem;
import com.pgoellner.karel.geometry.Coordinates;
import com.pgoellner.karel.geometry.Location;
import com.pgoellner.karel.geometry.Orientation;

import java.awt.*;
import java.util.*;
import java.util.List;

public final class World {
    private final BackupWorld originalState;

    private final CoordinateSystem expanse;

    private final List<Location<Color>> colours;
    private final Map<Coordinates, Integer> beepers;
    private final List<Location<Orientation>> walls;

    World(int x, int y) {
        this(x, y, ArgumentList.of(), ArgumentList.of(), ArgumentList.of());
    }

    World(int x, int y, List<Location<Orientation>> walls, List<Coordinates> beepers) {
        this(x, y, walls, beepers, ArgumentList.of());
    }

    public World(int x, int y, List<Location<Orientation>> walls, List<Coordinates> beepers, List<Location<Color>> colours) {
        originalState = new BackupWorld(walls, beepers);

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

    List<Coordinates> allCoordinates() {
        return expanse.allCoordinates();
    }

    void placeBeeper(Coordinates location) {
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

    List<Location<Orientation>> allWalls() {
        return new ArrayList<>(walls);
    }

    List<Location<Color>> allColours() {
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
                return position.y == expanse.yLimit();
            case EAST:
                return position.x == expanse.xLimit();
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


    void resetToOriginalState() {
        this.walls.clear();
        this.walls.addAll(originalState.walls);

        this.beepers.clear();
        for (Coordinates beeperLocation : originalState.beepers) {
            this.beepers.put(beeperLocation, 1 + this.beepers.getOrDefault(beeperLocation, 0));
        }
    }


    public String toString() {
        return String.format("Dim: (%s/%s) / Beepers: %s / Walls: %s",
                expanse.xLimit(),
                expanse.yLimit(),
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

final class BackupWorld {
    final List<Location<Orientation>> walls;
    final List<Coordinates> beepers;

    BackupWorld(List<Location<Orientation>> walls, List<Coordinates> beepers) {
        this.walls = new ArrayList<>(walls);
        this.beepers = new ArrayList<>(beepers);
    }
}
