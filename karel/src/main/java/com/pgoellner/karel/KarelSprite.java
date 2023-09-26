package com.pgoellner.karel;

import com.pgoellner.karel.geometry.CoordinateSystem;
import com.pgoellner.karel.geometry.Coordinates;
import com.pgoellner.karel.geometry.Orientation;

import java.awt.Polygon;
import java.util.List;
import java.util.stream.Collectors;

final class KarelSprite {
    private static final int nativeSideLength = 160;
    private final int topX;
    private final int topY;
    private final int rotationDegrees;
    private final Coordinates spriteCentre;
    private final double sideLengthFactor;

    public KarelSprite(Coordinates topLeftCorner, Orientation orientation, int sideLength) {
        this.topX = topLeftCorner.x;
        this.topY = topLeftCorner.y;

        switch (orientation) {
            case NORTH:
                rotationDegrees = 270;
                break;
            case SOUTH:
                rotationDegrees = 90;
                break;
            case WEST:
                rotationDegrees = 180;
                break;
            default:
                rotationDegrees = 0;
                break;
        }

        this.spriteCentre = new Coordinates(topX + sideLength / 2, topY + sideLength / 2);
        this.sideLengthFactor = (double) sideLength / nativeSideLength;
    }

    public List<Polygon> bodyParts() {
        return ArgumentList.of(ArgumentList.of(
                                new Coordinates(topX + (int) (sideLengthFactor * 37), topY),
                                new Coordinates(topX + (int) (sideLengthFactor * 110), topY),
                                new Coordinates(topX + (int) (sideLengthFactor * 110), topY + (int) (sideLengthFactor * 20)),
                                new Coordinates(topX + (int) (sideLengthFactor * 37), topY + (int) (sideLengthFactor * 20))
                        ),
                        ArgumentList.of(
                                new Coordinates(topX + (int) (sideLengthFactor * 37), topY),
                                new Coordinates(topX + (int) (sideLengthFactor * 54), topY),
                                new Coordinates(topX + (int) (sideLengthFactor * 54), topY + (int) (sideLengthFactor * 132)),
                                new Coordinates(topX + (int) (sideLengthFactor * 37), topY + (int) (sideLengthFactor * 116))
                        ),
                        ArgumentList.of(
                                new Coordinates(topX + (int) (sideLengthFactor * 110), topY),
                                new Coordinates(topX + (int) (sideLengthFactor * 136), topY + (int) (sideLengthFactor * 25)),
                                new Coordinates(topX + (int) (sideLengthFactor * 136), topY + (int) (sideLengthFactor * 132)),
                                new Coordinates(topX + (int) (sideLengthFactor * 110), topY + (int) (sideLengthFactor * 132))
                        ),
                        ArgumentList.of(
                                new Coordinates(topX + (int) (sideLengthFactor * 136), topY + (int) (sideLengthFactor * 87)),
                                new Coordinates(topX + (int) (sideLengthFactor * 136), topY + (int) (sideLengthFactor * 132)),
                                new Coordinates(topX + (int) (sideLengthFactor * 54), topY + (int) (sideLengthFactor * 132)),
                                new Coordinates(topX + (int) (sideLengthFactor * 54), topY + (int) (sideLengthFactor * 87))
                        )
                ).stream()
                .map(this::rotateAndConvertToPolygons)
                .collect(Collectors.toList());
    }

    public Polygon bodyWindow() {
        return rotateAndConvertToPolygons(ArgumentList.of(
                new Coordinates(topX + (int) (sideLengthFactor * 54), topY + (int) (sideLengthFactor * 20)),
                new Coordinates(topX + (int) (sideLengthFactor * 110), topY + (int) (sideLengthFactor * 20)),
                new Coordinates(topX + (int) (sideLengthFactor * 110), topY + (int) (sideLengthFactor * 87)),
                new Coordinates(topX + (int) (sideLengthFactor * 54), topY + (int) (sideLengthFactor * 87))
        ));
    }

    public Polygon bodyOutline() {
        return rotateAndConvertToPolygons(ArgumentList.of(
                new Coordinates(topX + (int) (sideLengthFactor * 37), topY),
                new Coordinates(topX + (int) (sideLengthFactor * 110), topY),
                new Coordinates(topX + (int) (sideLengthFactor * 136), topY + (int) (sideLengthFactor * 26)),
                new Coordinates(topX + (int) (sideLengthFactor * 136), topY + (int) (sideLengthFactor * 132)),
                new Coordinates(topX + (int) (sideLengthFactor * 53), topY + (int) (sideLengthFactor * 132)),
                new Coordinates(topX + (int) (sideLengthFactor * 37), topY + (int) (sideLengthFactor * 116))
        ));
    }

    public Mouth mouth() {
        return new Mouth();
    }

    public Polygon topLeg() {
        return rotateAndConvertToPolygons(ArgumentList.of(
                new Coordinates(topX + (int) (sideLengthFactor * 10), topY + (int) (sideLengthFactor * 86)),
                new Coordinates(topX + (int) (sideLengthFactor * 37), topY + (int) (sideLengthFactor * 86)),
                new Coordinates(topX + (int) (sideLengthFactor * 37), topY + (int) (sideLengthFactor * 100)),
                new Coordinates(topX + (int) (sideLengthFactor * 24), topY + (int) (sideLengthFactor * 100)),
                new Coordinates(topX + (int) (sideLengthFactor * 24), topY + (int) (sideLengthFactor * 120)),
                new Coordinates(topX + (int) (sideLengthFactor * 10), topY + (int) (sideLengthFactor * 120))
        ));
    }

    public Polygon bottomLeg() {
        return rotateAndConvertToPolygons(ArgumentList.of(
                new Coordinates(topX + (int) (sideLengthFactor * 82), topY + (int) (sideLengthFactor * 133)),
                new Coordinates(topX + (int) (sideLengthFactor * 96), topY + (int) (sideLengthFactor * 133)),
                new Coordinates(topX + (int) (sideLengthFactor * 96), topY + (int) (sideLengthFactor * 146)),
                new Coordinates(topX + (int) (sideLengthFactor * 116), topY + (int) (sideLengthFactor * 146)),
                new Coordinates(topX + (int) (sideLengthFactor * 116), topY + (int) (sideLengthFactor * 160)),
                new Coordinates(topX + (int) (sideLengthFactor * 82), topY + (int) (sideLengthFactor * 160))
        ));
    }

    private Polygon rotateAndConvertToPolygons(List<Coordinates> vertices) {
        return asPolygon(CoordinateSystem.rotate(spriteCentre, vertices, rotationDegrees));
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

    public class Mouth {
        final Coordinates left;
        final Coordinates right;

        public Mouth() {

            List<Coordinates> rotatedVertices = CoordinateSystem.rotate(
                    spriteCentre,
                    ArgumentList.of(
                            new Coordinates(topX + (int) (sideLengthFactor * 83), topY + (int) (sideLengthFactor * 109)),
                            new Coordinates(topX + (int) (sideLengthFactor * 107), topY + (int) (sideLengthFactor * 109))
                    ),
                    rotationDegrees);

            left = rotatedVertices.get(0);
            right = rotatedVertices.get(1);
        }
    }
}
