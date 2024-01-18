package com.pgoellner.karel.errors;

import com.pgoellner.karel.localization.TextLabels;

public class WallCollision extends KarelError {
    @Override
    public String getMessage() {
        return TextLabels.systemDefaults().wallCollisionErrorMessage();
    }
}
