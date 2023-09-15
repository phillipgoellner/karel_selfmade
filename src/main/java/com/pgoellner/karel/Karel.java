package com.pgoellner.karel;

import com.pgoellner.karel.geometry.Coordinates;
import com.pgoellner.karel.geometry.Orientation;
import com.pgoellner.karel.parse.WorldFileParser;

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
        String programName = determineProgramName();

        String worldFileName = programName.replace("com.pgoellner.karel.", "");

        WorldFileParser parser = new WorldFileParser(
                String.format("src/worlds/%s.w", worldFileName),
                String.format("worlds/%s.w", worldFileName)
        );
        World world = parser.fromDescription();

        Karel karel = karelInstance(programName);
        karel.world = world;
        karel.currentLocation = parser.karelStartingPoint();
        karel.currentOrientation = parser.karelStartingOrientation();

        UiBuilder.createWindow(
                karel,
                world,
                programName
        );
    }

    private static String determineProgramName() {
        String javaCommandPropertyContent = System.getProperty("sun.java.command");
        if (javaCommandPropertyContent != null) {
            return System.getProperty("sun.java.command");
        }
        return "COULD NOT DETERMINE PROGRAM NAME";
    }

    private static Karel karelInstance(String fromProgramName) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return (Karel) Class.forName(fromProgramName).newInstance();
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
        if (world.viewIsBlocked(currentLocation, currentOrientation)) {
            throw new WallCollision();
        }
        currentLocation = currentLocation.plus(direction());
        pause();
    }

    public final void turnLeft() {
        currentOrientation = Orientation.rotateLeft(currentOrientation);
        pause();
    }

    public final void putBeeper() {
        world.putBeeper(currentLocation);
        pause();
    }

    public final void pickBeeper() {
        world.removeBeeper(currentLocation);
        pause();
    }

    public final boolean beeperIsPresent() {
        return world.numberOfBeepersAt(currentLocation) > 0;
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
            case NORTH:
                return new Coordinates(0, 1);
            case EAST:
                return new Coordinates(1, 0);
            case SOUTH:
                return new Coordinates(0, -1);
            default:
                return new Coordinates(-1, 0);
        }
    }
}
