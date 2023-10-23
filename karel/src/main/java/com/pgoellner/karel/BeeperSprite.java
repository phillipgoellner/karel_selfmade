package com.pgoellner.karel;

import com.pgoellner.karel.geometry.CoordinateSystem;
import com.pgoellner.karel.geometry.Coordinates;
import com.pgoellner.karel.geometry.Orientation;

import java.awt.*;
import java.util.List;

final class BeeperSprite {
    private static final int nativeSideLength = 160;
    private final int topX;
    private final int topY;
    private final Coordinates spriteCentre;
    private final double sideLengthFactor;

    public BeeperSprite(Coordinates topLeftCorner, int sideLength) {
        this.topX = topLeftCorner.x;
        this.topY = topLeftCorner.y;

        this.spriteCentre = new Coordinates(topX + sideLength / 2, topY + sideLength / 2);
        this.sideLengthFactor = (double) sideLength / nativeSideLength;
    }

    public Polygon outline() {
        return asPolygon(CoordinateSystem.rotate(spriteCentre, ArgumentList.of(
                new Coordinates(topX + (int) (sideLengthFactor * 32), topY + (int) (sideLengthFactor * 32)),
                new Coordinates(topX + (int) (sideLengthFactor * 32), topY + (int) (sideLengthFactor * 128)),
                new Coordinates(topX + (int) (sideLengthFactor * 128), topY + (int) (sideLengthFactor * 128)),
                new Coordinates(topX + (int) (sideLengthFactor * 128), topY + (int) (sideLengthFactor * 32))
        ), 45));
    }

    private static Polygon asPolygon(List<Coordinates> vertices) {
        int[] xs = vertices.stream().mapToInt(coordinate -> coordinate.x).toArray();
        int[] ys = vertices.stream().mapToInt(coordinate -> coordinate.y).toArray();

        return new Polygon(
                xs,
                ys,
                vertices.size()
        );
    }
}
