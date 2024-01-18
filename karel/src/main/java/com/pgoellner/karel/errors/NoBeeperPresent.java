package com.pgoellner.karel.errors;

import com.pgoellner.karel.localization.TextLabels;

public class NoBeeperPresent extends KarelError {
    @Override
    public String getMessage() {
        return TextLabels.systemDefaults().noBeepersErrorMessage();
    }
}
