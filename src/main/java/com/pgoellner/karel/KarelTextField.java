package com.pgoellner.karel;

import javax.swing.*;
import java.awt.*;

public class KarelTextField extends JPanel {
    private final String textToRender;
    private Color currentColour;

    KarelTextField(String text) {
        textToRender = text;
        currentColour = Color.BLACK;
        setPreferredSize(new Dimension(100, 30));
    }

    @Override
    protected void paintComponent(Graphics g) {
        final Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(currentColour);
        g2D.fillRect(0, 0, getWidth(), getHeight());
    }

    public void displayRunning() {
        currentColour = new Color(100, 95, 0);
    }

    public void displayError() {
        currentColour = Color.RED;
    }

    public void displaySucceeded() {
        currentColour = Color.GREEN;
    }
}
