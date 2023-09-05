package com.pgoellner.karel;

import javax.swing.*;

public class Karel {
    private Coordinates currentLocation;
    private Orientation currentOrientation;

    private final World world;

    private JFrame updateTarget;

    Karel(World world, Coordinates startingPoint, Orientation startingOrientation) {
        currentLocation = startingPoint;
        currentOrientation = startingOrientation;

        this.world = world;
    }

    public static void main(String[] args) {
        WorldFileParser parser = new WorldFileParser();
        World world = parser.fromDescription();
        UiBuilder.createWindow(new Karel(world, parser.karelStartingPoint(), parser.karelStartingOrientation()), world);
    }

    void registerUpdateCallback(JFrame target) {
        this.updateTarget = target;
    }

    public void run() {
        turnLeft();
        putBeeper();
        move();
        putBeeper();
        move();
        putBeeper();
        move();
    }

    public final void move() {
        currentLocation = currentLocation.plus(direction());
        renderUpdateAndPause();
    }

    public final void turnLeft() {
        currentOrientation = Orientation.rotateLeft(currentOrientation);
        renderUpdateAndPause();
    }

    public final void putBeeper() {
        world.putBeeper(currentLocation.minus(Coordinates.UNIT));
        renderUpdateAndPause();
    }

    private void renderUpdateAndPause() {
        try {
            Thread.sleep(200L);
        } catch (InterruptedException interruptedException) {
            System.err.printf("Something really bad went wrong: %s%n", interruptedException);
            System.err.println("Please get mad at the developer");
        }
        updateTarget.repaint();
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
