package com.pgoellner.karel.parse;

import com.pgoellner.karel.World;
import com.pgoellner.karel.geometry.Coordinates;
import com.pgoellner.karel.geometry.Orientation;

public interface WorldInformationProvider {
    World fromDescription();

    Coordinates karelStartingPoint();

    Orientation karelStartingOrientation();
}
