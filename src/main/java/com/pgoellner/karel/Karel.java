package com.pgoellner.karel;

import javax.swing.*;

public class Karel {
    private Coordinates currentLocation;
    private Orientation currentOrientation;

    private final World world;

    private JFrame updateTarget;

    public Karel(World world) {
        currentLocation = new Coordinates(4, 4);
        currentOrientation = Orientation.NORTH;

        this.world = world;
    }

    public static void main(String[] args) {
        World world = new World(9, 9);
        UiBuilder.createWindow(new Karel(world), world);
    }

    void registerUpdateCallback(JFrame target) {
        this.updateTarget = target;
    }

    public void run() {
        turnLeft();
        putBeeper();
        move();
    }

    public final void move() {
        currentLocation = currentLocation.plusX();
        updateTarget.repaint();
    }

    public final void turnLeft() {
        currentOrientation = Orientation.rotateLeft(currentOrientation);
        updateTarget.repaint();
    }

    public final void putBeeper() {
        world.putBeeper(currentLocation.minus(Coordinates.UNIT));
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
}
