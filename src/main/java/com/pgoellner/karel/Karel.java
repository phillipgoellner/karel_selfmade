package com.pgoellner.karel;

import javax.swing.*;

public class Karel {
    private Coordinates currentLocation;
    private Orientation currentOrientation;

    private JFrame updateTarget;

    public Karel() {
        currentLocation = new Coordinates(4, 4);
        currentOrientation = Orientation.NORTH;
    }

    public static void main(String[] args) {
        UiBuilder.createWindow(new Karel());
    }

    void registerUpdateCallback(JFrame target) {
        this.updateTarget = target;
    }

    public void run() {
        turnLeft();
    }

    public final void move() {
        currentLocation = currentLocation.plusX();
        updateTarget.repaint();
    }

    public final void turnLeft() {
        currentOrientation = Orientation.rotateLeft(currentOrientation);
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
