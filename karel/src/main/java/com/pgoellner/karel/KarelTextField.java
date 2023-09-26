package com.pgoellner.karel;

import javax.swing.*;
import java.awt.*;

public class KarelTextField extends JPanel {
    private String textToRender;
    private Color currentColour;

    KarelTextField(String text) {
        textToRender = text;
        currentColour = Color.BLACK;
        setPreferredSize(new Dimension(100, 30));
    }

    @Override
    protected void paintComponent(Graphics g) {
        final Graphics2D g2D = (Graphics2D) g;
        final String basicFontName = g2D.getFont().getFontName();

        g2D.setFont(new Font(basicFontName, Font.BOLD, 16));
        g2D.setColor(currentColour);
        g2D.drawString(textToRender, 10, 20);
    }

    public void reset() {
        currentColour = Color.BLACK;
        textToRender = "Program reset";
    }

    public void displayRunning() {
        currentColour = Color.ORANGE;
        textToRender = "Running...";
    }

    public void displayError() {
        currentColour = Color.RED;
        textToRender = "Error :(";
    }

    public void displaySucceeded() {
        currentColour = Color.GREEN;
        textToRender = "Finished!";
    }
}
