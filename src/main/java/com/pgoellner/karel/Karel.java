package com.pgoellner.karel;

import javax.swing.*;

public class Karel {
    private Coordinates currentLocation;
    private JFrame updateTarget;

    public Karel() {
        currentLocation = new Coordinates(4, 4);
    }

    public static void main(String[] args) {
        UiBuilder.createWindow(new Karel());
    }

    void registerUpdateCallback(JFrame target) {
        this.updateTarget = target;
    }

    public void run() {
        move();
    }

    public void move() {
        currentLocation = currentLocation.plusX();
        updateTarget.repaint();
    }

    int x() {
        return currentLocation.x;
    }

    int y() {
        return currentLocation.y;
    }
}
