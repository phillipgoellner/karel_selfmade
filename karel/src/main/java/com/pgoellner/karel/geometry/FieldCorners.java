package com.pgoellner.karel.geometry;

public class FieldCorners {
    public final Coordinates topLeft;
    public final Coordinates topRight;
    public final Coordinates bottomLeft;
    public final Coordinates bottomRight;

    public FieldCorners(Coordinates topLeft, Coordinates topRight, Coordinates bottomLeft, Coordinates bottomRight) {
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
