package com.pgoellner.karel;

import com.pgoellner.karel.geometry.Coordinates;
import com.pgoellner.karel.geometry.Orientation;
import com.pgoellner.karel.parse.WorldFileParser;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class Karel {
    private Coordinates currentLocation;
    private Orientation currentOrientation;

    private World world;

    public Karel() {
        this(new World(5, 5), new Coordinates(1, 1), Orientation.EAST);
    }

    Karel(World world, Coordinates startingPoint, Orientation startingOrientation) {
        currentLocation = startingPoint;
        currentOrientation = startingOrientation;

        this.world = world;
    }

    public static void main(String[] args) throws Exception {
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        String programStart = runtimeMxBean
                .getInputArguments()
                .stream()
                .filter(line -> line.startsWith("-Dsun.java.command"))
                .map(line -> line.replace("-Dsun.java.command=", ""))
                .findFirst()
                .orElse("Karel (nothing found)");

        String worldFilePath = String.format("src/worlds/%s.w", programStart.replace("com.pgoellner.karel.", ""));

        WorldFileParser parser = new WorldFileParser(worldFilePath);
        World world = parser.fromDescription();

        Karel karel = (Karel) Class.forName(programStart).newInstance();
        karel.world = world;
        karel.currentLocation = parser.karelStartingPoint();
        karel.currentOrientation = parser.karelStartingOrientation();

        UiBuilder.createWindow(
                karel,
                world,
                programStart
        );
    }

    public void run() {
        // to be overridden in subclass
    }

    private void pause() {
        try {
            Thread.sleep(70L);
        } catch (InterruptedException interruptedException) {
            System.err.printf("Something really bad went wrong: %s%n", interruptedException);
            System.err.println("Please get mad at the developer");
        }
    }

    public final void move() {
        currentLocation = currentLocation.plus(direction());
        pause();
    }

    public final void turnLeft() {
        currentOrientation = Orientation.rotateLeft(currentOrientation);
        pause();
    }

    public final void putBeeper() {
        world.putBeeper(currentLocation.minus(Coordinates.UNIT));
        pause();
    }

    public final void pickBeeper() {
        world.removeBeeper(currentLocation.minus(Coordinates.UNIT));
        pause();
    }

    public final boolean beeperIsPresent() {
        return world.numberOfBeepersAt(currentLocation.minus(Coordinates.UNIT)) > 0;
    }

    public final boolean facingNorth() {
        return currentOrientation == Orientation.NORTH;
    }
    public final boolean facingEast() {
        return currentOrientation == Orientation.EAST;
    }
    public final boolean facingSouth() {
        return currentOrientation == Orientation.SOUTH;
    }
    public final boolean facingWest() {
        return currentOrientation == Orientation.WEST;
    }

    public final boolean frontIsClear() {
        return !world.viewIsBlocked(currentLocation, currentOrientation);
    }
    public final boolean leftIsClear() {
        return !world.viewIsBlocked(currentLocation, Orientation.rotateLeft(currentOrientation));
    }
    public final boolean rightIsClear() {
        return !world.viewIsBlocked(
                currentLocation,
                Orientation.rotateLeft(Orientation.rotateLeft(Orientation.rotateLeft(currentOrientation)))
        );
    }

    public final boolean frontIsBlocked() {
        return world.viewIsBlocked(currentLocation, currentOrientation);
    }
    public final boolean leftIsBlocked() {
        return world.viewIsBlocked(currentLocation, Orientation.rotateLeft(currentOrientation));
    }
    public final boolean rightIsBlocked() {
        return world.viewIsBlocked(
                currentLocation,
                Orientation.rotateLeft(Orientation.rotateLeft(Orientation.rotateLeft(currentOrientation)))
        );
    }

    int x() {
        return currentLocation.x;
    }

    int y() {
        return currentLocation.y;
    }

    Orientation facing() {
        return this.currentOrientation;
    }

    private Coordinates direction() {
        switch (this.currentOrientation) {
            case NORTH: return new Coordinates(0, 1);
            case EAST: return new Coordinates(1, 0);
            case SOUTH: return new Coordinates(0, -1);
            default: return new Coordinates(-1, 0);
        }
    }
}
