package com.pgoellner.karel;

import com.pgoellner.karel.geometry.Coordinates;
import com.pgoellner.karel.geometry.FieldCorners;
import com.pgoellner.karel.geometry.Location;
import com.pgoellner.karel.geometry.Orientation;

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
        final Stroke defaultStroke = drawer.getStroke();
        final BasicStroke broadStroke = new BasicStroke(1.5f);

        paintBackgroundWhite(drawer);
        fillFieldWithDots(drawer);
        drawColours(drawer);

        drawer.setStroke(broadStroke);

        drawBeepers(drawer);

        drawer.setStroke(defaultStroke);

        drawKarel(drawer);

        drawer.setStroke(broadStroke);

        drawWalls(drawer);
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
                drawer.drawLine(x * spriteSide() + xOffset - 1, y * spriteSide() + yOffset, x * spriteSide() + xOffset + 1, y * spriteSide() + yOffset);
                drawer.drawLine(x * spriteSide() + xOffset, y * spriteSide() + yOffset - 1, x * spriteSide() + xOffset, y * spriteSide() + yOffset + 1);
            }
        }
    }

    private void drawBeepers(Graphics2D drawer) {
        final int xOffset = scale(getWidth() - systemWidthPx(), 50);
        final int yOffset = scale(getHeight() - systemHeightPx(), 50);

        for (Coordinates point : world.allCoordinates()) {
            final int numberOfBeepers = world.numberOfBeepersAt(point);

            if (numberOfBeepers > 0) {
                final Coordinates renderCoordinates = point.mirrorOnY(fieldHeight());

                final int _x = (renderCoordinates.x - 1) * spriteSide() + xOffset;
                final int _y = (renderCoordinates.y - 1) * spriteSide() + yOffset;

                final int beeperMargin = scale(spriteSide(), 10);

                Polygon beeperFigure = new Polygon(
                        new int[]{
                                _x + scale(spriteSide(), 50),
                                _x + spriteSide() - beeperMargin,
                                _x + scale(spriteSide(), 50),
                                _x + beeperMargin
                        },
                        new int[]{
                                _y + beeperMargin,
                                _y + scale(spriteSide(), 50),
                                _y + spriteSide() - beeperMargin,
                                _y + scale(spriteSide(), 50)
                        },
                        4
                );

                drawer.setColor(Color.LIGHT_GRAY);
                drawer.fillPolygon(beeperFigure);
                drawer.setColor(Color.DARK_GRAY);
                drawer.drawPolygon(beeperFigure);

                if (numberOfBeepers > 1) {
                    drawer.drawString(
                            "" + numberOfBeepers,
                            _x + scale(spriteSide(), 50) - 4,
                            _y + scale(spriteSide(), 50) + 5
                    );
                }
            }
        }
    }

    private void drawKarel(Graphics2D drawer) {
        final Coordinates location = karel.position();
        final FieldCorners corners = getRenderCorners(location.mirrorOnY(fieldHeight()).minus(Coordinates.UNIT));
        final Coordinates topLeftCorner = corners.topLeft;

        drawer.setColor(Color.RED);
        drawer.fillRect(
                topLeftCorner.x + scale(spriteSide(), 10),
                topLeftCorner.y + scale(spriteSide(), 10),
                scale(spriteSide(), 80),
                scale(spriteSide(), 80)
        );

        drawer.setColor(Color.BLACK);
        switch (karel.facing()) {
            case EAST:
                drawer.drawLine(topLeftCorner.x + scale(spriteSide(), 50), topLeftCorner.y + scale(spriteSide(), 50), topLeftCorner.x + spriteSide(), topLeftCorner.y + scale(spriteSide(), 50));
                break;
            case SOUTH:
                drawer.drawLine(topLeftCorner.x + scale(spriteSide(), 50), topLeftCorner.y + spriteSide(), topLeftCorner.x + scale(spriteSide(), 50), topLeftCorner.y + scale(spriteSide(), 50));
                break;
            case WEST:
                drawer.drawLine(topLeftCorner.x, topLeftCorner.y + scale(spriteSide(), 50), topLeftCorner.x + scale(spriteSide(), 50), topLeftCorner.y + scale(spriteSide(), 50));
                break;
            default:
                drawer.drawLine(topLeftCorner.x + scale(spriteSide(), 50), topLeftCorner.y, topLeftCorner.x + scale(spriteSide(), 50), topLeftCorner.y + scale(spriteSide(), 50));
                break;
        }
    }

    private void drawColours(Graphics2D drawer) {
        for (Location<Color> colourLocation : world.allColours()) {
            drawer.setColor(colourLocation.content);
            FieldCorners renderCorners = getRenderCorners(colourLocation.coordinates.mirrorOnY(world.yDimension()).minus(Coordinates.UNIT));

            Coordinates topLeft = renderCorners.topLeft;
            drawer.fillRect(topLeft.x, topLeft.y, spriteSide(), spriteSide());
        }
    }

    private void drawWalls(Graphics2D drawer) {
        drawer.setColor(Color.BLACK);

        for (Location<Orientation> wall : world.allWalls()) {
            final FieldCorners renderCorners = getRenderCorners(wall.coordinates.mirrorOnY(world.yDimension()).minus(Coordinates.UNIT));

            final Coordinates topLeft = renderCorners.topLeft;
            final Coordinates topRight = renderCorners.topRight;
            final Coordinates bottomLeft = renderCorners.bottomLeft;
            final Coordinates bottomRight = renderCorners.bottomRight;

            switch (wall.content) {
                case NORTH: {
                    drawer.drawLine(
                            topLeft.x,
                            topLeft.y,
                            topRight.x,
                            topRight.y
                    );
                    break;
                }
                case EAST: {
                    drawer.drawLine(
                            topRight.x,
                            topRight.y,
                            bottomRight.x,
                            bottomRight.y
                    );
                    break;
                }
                case SOUTH: {
                    drawer.drawLine(
                            bottomLeft.x,
                            bottomLeft.y,
                            bottomRight.x,
                            bottomRight.y
                    );
                    break;
                }
                default: {
                    drawer.drawLine(
                            topLeft.x,
                            topLeft.y,
                            bottomLeft.x,
                            bottomLeft.y
                    );
                    break;
                }
            }
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

    private FieldCorners getRenderCorners(Coordinates atWorldPosition) {
        final int xOffset = scale(getWidth() - systemWidthPx(), 50);
        final int yOffset = scale(getHeight() - systemHeightPx(), 50);

        int spriteLeftX = atWorldPosition.x * spriteSide() + xOffset;
        int spriteTopY = atWorldPosition.y * spriteSide() + yOffset;

        return new FieldCorners(
                new Coordinates(spriteLeftX, spriteTopY),
                new Coordinates(spriteLeftX + spriteSide(), spriteTopY),
                new Coordinates(spriteLeftX, spriteTopY + spriteSide()),
                new Coordinates(spriteLeftX + spriteSide(), spriteTopY + spriteSide())
        );
    }
}
