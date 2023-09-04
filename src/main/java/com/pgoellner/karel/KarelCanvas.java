package com.pgoellner.karel;

import javax.swing.*;
import java.awt.*;

public class KarelCanvas extends JPanel {
    private final transient Karel karel;
    private final transient World world;

    public KarelCanvas(Karel karel, World world) {
        this.karel = karel;
        this.world = world;
    }

    private int spriteSide() {
        int smallestSide = Math.min(getWidth(), getHeight());
        return scale(smallestSide - 20, 95) / Math.max(fieldWidth(), fieldHeight());
    }

    private int systemWidthPx() {
        return spriteSide() * fieldWidth();
    }

    private int systemHeightPx() {
        return spriteSide() * fieldHeight();
    }

    private int fieldWidth() {
        return world.xDimension();
    }

    private int fieldHeight() {
        return world.yDimension();
    }

    private int scale(int measure, int byPercentage) {
        return (int) (measure * (byPercentage / 100d));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D drawer = (Graphics2D) g;

        paintBackgroundWhite(drawer);
        fillFieldWithDots(drawer);
        drawBeepers(drawer);
        drawKarel(new Coordinates(karel.x(), karel.y()), karel.facing(), drawer);
        drawCoordinateBox(drawer);
    }

    private void paintBackgroundWhite(Graphics2D drawer) {
        drawer.setColor(Color.WHITE);
        drawer.fillRect(0, 0, getWidth(), getHeight());
    }

    private void fillFieldWithDots(Graphics2D drawer) {
        drawer.setColor(Color.BLACK);

        final int xOffset = scale(getWidth() - systemWidthPx() + spriteSide(), 50);
        final int yOffset = scale(getHeight() - systemHeightPx() + spriteSide(), 50);

        for (int x = 0; x < fieldWidth(); x++) {
            for (int y = 0; y < fieldHeight(); y++) {
                drawer.fillRect(x * spriteSide() + xOffset, y * spriteSide() + yOffset, 2, 2);
            }
        }
    }

    private void drawBeepers(Graphics2D drawer) {
        final int xOffset = scale(getWidth() - systemWidthPx(), 50);
        final int yOffset = scale(getHeight() - systemHeightPx(), 50);

        for (int x = 0; x < fieldWidth(); x++) {
            for (int y = 0; y < fieldHeight(); y++) {
                final Coordinates worldCoordinates = new Coordinates(x, y);

                if (world.numberOfBeepersAt(worldCoordinates) > 0) {
                    final Coordinates renderCoordinates = worldCoordinates.plus(Coordinates.UNIT).mirrorOnY(fieldHeight());

                    final int _x = (renderCoordinates.x - 1) * spriteSide() + xOffset;
                    final int _y = (renderCoordinates.y - 1) * spriteSide() + yOffset;

                    Polygon beeperFigure = new Polygon(
                            new int[]{
                                    _x + scale(spriteSide(), 50),
                                    _x + spriteSide(),
                                    _x + scale(spriteSide(), 50),
                                    _x
                            },
                            new int[]{
                                    _y,
                                    _y + scale(spriteSide(), 50),
                                    _y + spriteSide(),
                                    _y + scale(spriteSide(), 50)
                            },
                            4
                    );

                    drawer.setColor(Color.LIGHT_GRAY);
                    drawer.fillPolygon(beeperFigure);
                    drawer.setColor(Color.BLACK);
                    drawer.drawPolygon(beeperFigure);
                }
            }
        }
    }

    private void drawKarel(Coordinates location, Orientation orientation, Graphics2D drawer) {
        final int xOffset = scale(getWidth() - systemWidthPx(), 50);
        final int yOffset = scale(getHeight() - systemHeightPx(), 50);

        int spriteTopY = (location.mirrorOnY(fieldHeight()).y - 1) * spriteSide() + yOffset;
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
            case EAST:
                drawer.drawLine(spriteLeftX + scale(spriteSide(), 50), spriteTopY + scale(spriteSide(), 50), spriteLeftX + spriteSide(), spriteTopY + scale(spriteSide(), 50));
                break;
            case SOUTH:
                drawer.drawLine(spriteLeftX + scale(spriteSide(), 50), spriteTopY + spriteSide(), spriteLeftX + scale(spriteSide(), 50), spriteTopY + scale(spriteSide(), 50));
                break;
            case WEST:
                drawer.drawLine(spriteLeftX, spriteTopY + scale(spriteSide(), 50), spriteLeftX + scale(spriteSide(), 50), spriteTopY + scale(spriteSide(), 50));
                break;
            default:
                drawer.drawLine(spriteLeftX + scale(spriteSide(), 50), spriteTopY, spriteLeftX + scale(spriteSide(), 50), spriteTopY + scale(spriteSide(), 50));
                break;
        }
    }

    private void drawCoordinateBox(Graphics2D drawer) {
        drawer.setColor(Color.BLACK);

        final int widthMargin = (getWidth() - systemWidthPx()) / 2;
        final int heightMargin = (getHeight() - systemHeightPx()) / 2;

        drawer.drawRect(
                widthMargin,
                heightMargin,
                systemWidthPx(),
                systemHeightPx()
        );

        for (int x = 0; x < fieldWidth(); x++) {
            drawer.drawString(
                    "" + (x + 1),
                    x * spriteSide() + widthMargin + scale(spriteSide(), 50) - 3,
                    systemHeightPx() + heightMargin + 20
            );
        }

        for (int y = 0; y < fieldHeight(); y++) {
            drawer.drawString(
                    "" + (fieldHeight() - y),
                    widthMargin - 20,
                    y * spriteSide() + heightMargin + scale(spriteSide(), 50) + 3
            );
        }
    }
}
