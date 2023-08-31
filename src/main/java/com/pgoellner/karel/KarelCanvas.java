package com.pgoellner.karel;

import javax.swing.*;
import java.awt.*;

public class KarelCanvas extends JPanel {
    private final int fieldWidth;
    private final int fieldHeight;
    private final transient Karel karel;

    public KarelCanvas(Karel karel) {
        this.karel = karel;

        this.fieldWidth = 9;
        this.fieldHeight = 9;
    }

    private int spriteSide() {
        int smallestSide = Math.min(getWidth(), getHeight());
        return scale(smallestSide - 20, 95) / Math.max(fieldWidth, fieldHeight);
    }

    private int systemWidth() {
        return spriteSide() * fieldWidth;
    }

    private int systemHeight() {
        return spriteSide() * fieldHeight;
    }

    private int scale(int measure, int byPercentage) {
        return (int) (measure * (byPercentage / 100d));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D drawer = (Graphics2D) g;

        paintBackgroundWhite(drawer);
        fillFieldWithDots(drawer);
        drawKarel(new Coordinates(karel.x(), karel.y()), karel.facing(), drawer);
        drawCoordinateBox(drawer);
    }

    private void paintBackgroundWhite(Graphics2D drawer) {
        drawer.setColor(Color.WHITE);
        drawer.fillRect(0, 0, getWidth(), getHeight());
    }

    private void fillFieldWithDots(Graphics2D drawer) {
        drawer.setColor(Color.BLACK);

        final int xOffset = scale(getWidth() - systemWidth() + spriteSide(), 50);
        final int yOffset = scale(getHeight() - systemHeight() + spriteSide(), 50);

        for (int x = 0; x < fieldWidth; x++) {
            for (int y = 0; y < fieldHeight; y++) {
                drawer.fillRect(x * spriteSide() + xOffset, y * spriteSide() + yOffset, 2, 2);
            }
        }
    }

    private void drawKarel(Coordinates location, Orientation orientation, Graphics2D drawer) {
        final int xOffset = scale(getWidth() - systemWidth(), 50);
        final int yOffset = scale(getHeight() - systemHeight(), 50);

        int spriteTopY = (location.mirrorOnY(fieldHeight).y - 1) * spriteSide() + yOffset;
        int spriteLeftX = (location.x - 1) * spriteSide() + xOffset;

        drawer.setColor(Color.RED);
        drawer.fillRect(
                spriteLeftX,
                spriteTopY,
                spriteSide(),
                spriteSide()
        );


        drawer.setColor(Color.BLACK);
        switch (orientation) {
            case EAST: drawer.drawLine(spriteLeftX + scale(spriteSide(), 50), spriteTopY + scale(spriteSide(), 50), spriteLeftX + spriteSide(), spriteTopY + scale(spriteSide(), 50)); break;
            case SOUTH: drawer.drawLine(spriteLeftX + scale(spriteSide(), 50), spriteTopY + spriteSide(), spriteLeftX + scale(spriteSide(), 50), spriteTopY + scale(spriteSide(), 50)); break;
            case WEST: drawer.drawLine(spriteLeftX, spriteTopY + scale(spriteSide(), 50), spriteLeftX + scale(spriteSide(), 50), spriteTopY + scale(spriteSide(), 50)); break;
            default: drawer.drawLine(spriteLeftX + scale(spriteSide(), 50), spriteTopY, spriteLeftX + scale(spriteSide(), 50), spriteTopY + scale(spriteSide(), 50)); break;
        }
    }

    private void drawCoordinateBox(Graphics2D drawer) {
        drawer.setColor(Color.BLACK);

        final int widthMargin = (getWidth() - systemWidth()) / 2;
        final int heightMargin = (getHeight() - systemHeight()) / 2;

        drawer.drawRect(
                widthMargin,
                heightMargin,
                systemWidth(),
                systemHeight()
        );

        for (int x = 0; x < fieldWidth; x++) {
            drawer.drawString(
                    "" + (x + 1),
                    x * spriteSide() + widthMargin + scale(spriteSide(), 50) - 3,
                    systemHeight() + heightMargin + 20
            );
        }

        for (int y = 0; y < fieldHeight; y++) {
            drawer.drawString(
                    "" + (fieldHeight - y),
                    widthMargin - 20,
                    y * spriteSide() + heightMargin + scale(spriteSide(), 50) + 3
            );
        }
    }
}
