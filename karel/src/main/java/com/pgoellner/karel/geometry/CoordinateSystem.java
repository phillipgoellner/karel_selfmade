package com.pgoellner.karel.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CoordinateSystem {
    private final Coordinates furthestCorner;

    private final List<Coordinates> allCoordinates;

    public CoordinateSystem(Coordinates origin, Coordinates furthestCorner) {
        this.furthestCorner = furthestCorner;

        this.allCoordinates = new ArrayList<>();

        for (int x = origin.x; x <= furthestCorner.x; x++) {
            for (int y = origin.y; y <= furthestCorner.y; y++) {
                this.allCoordinates.add(new Coordinates(x, y));
            }
        }
    }

    public int xLimit() {
        return furthestCorner.x;
    }

    public int yLimit() {
        return furthestCorner.y;
    }

    public List<Coordinates> allCoordinates() {
        return new ArrayList<>(this.allCoordinates);
    }

    public static List<Coordinates> rotate(Coordinates centreOfRotation, List<Coordinates> pointsToRotate, int degrees) {
        return pointsToRotate
                .stream()
                .map(coordinates -> rotate(centreOfRotation, coordinates, degrees))
                .collect(Collectors.toList());
    }

    private static Coordinates rotate(Coordinates centreOfRotation, Coordinates pointToRotate, int degrees) {
        if (degrees == 45) {
            return rotate45Degrees(centreOfRotation, pointToRotate);
        }

        final Coordinates pointRelativeToOrigin = pointToRotate.minus(centreOfRotation);

        final Coordinates pointRotatedAroundOrigin = new Coordinates(
                pointRelativeToOrigin.x * cos(degrees) - pointRelativeToOrigin.y * sin(degrees),
                pointRelativeToOrigin.x * sin(degrees) + pointRelativeToOrigin.y * cos(degrees)
        );

        return pointRotatedAroundOrigin.plus(centreOfRotation);
    }

    private static Coordinates rotate45Degrees(Coordinates centreOfRotation, Coordinates pointToRotate) {
        final Coordinates pointRelativeToOrigin = pointToRotate.minus(centreOfRotation);

        final Coordinates pointRotatedAroundOrigin = new Coordinates(
                (int) (0.707d * (pointRelativeToOrigin.x - pointRelativeToOrigin.y)),
                (int) (0.707d * (pointRelativeToOrigin.x + pointRelativeToOrigin.y))
        );

        return pointRotatedAroundOrigin.plus(centreOfRotation);
    }

    private static int sin(int degrees) {
        if (degrees >= 360) {
            return sin(degrees - 360);
        } else if (degrees <= -360) {
            return sin(degrees + 360);
        }

        switch (degrees) {
            case 90:
            case -270:
                return 1;
            case -90:
            case 270:
                return -1;
            default:
                return 0;
        }
    }

    private static int cos(int degrees) {
        if (degrees >= 360) {
            return cos(degrees - 360);
        } else if (degrees <= -360) {
            return cos(degrees + 360);
        }

        switch (degrees) {
            case 0:
                return 1;
            case -180:
            case 180:
                return -1;
            default:
                return 0;
        }
    }
}
