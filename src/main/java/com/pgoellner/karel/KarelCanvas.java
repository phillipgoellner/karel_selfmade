package com.pgoellner.karel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
        drawWalls(world.allWalls(), drawer);
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
        switch (orientation) {
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

    private void drawWalls(List<WallLocation> walls, Graphics2D drawer) {
        drawer.setColor(Color.BLACK);

        for (WallLocation wall : walls) {
            final FieldCorners renderCorners = getRenderCorners(wall.coordinates.mirrorOnY(world.yDimension()).minus(Coordinates.UNIT));

            final Coordinates topLeft = renderCorners.topLeft;
            final Coordinates topRight = renderCorners.topRight;
            final Coordinates bottomLeft = renderCorners.bottomLeft;
            final Coordinates bottomRight = renderCorners.bottomRight;

            switch (wall.orientation) {
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

class FieldCorners {
    public final Coordinates topLeft;
    public final Coordinates topRight;
    public final Coordinates bottomLeft;
    public final Coordinates bottomRight;

    FieldCorners(Coordinates topLeft, Coordinates topRight, Coordinates bottomLeft, Coordinates bottomRight) {
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}