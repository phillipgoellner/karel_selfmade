package com.pgoellner.karel.localization;

import java.util.Locale;

public interface TextLabels {
    static TextLabels systemDefaults() {
        switch (Locale.getDefault().getLanguage()) {
            case "de": return new GermanLabels();
            case "pt": return new PortugueseLabels();
            default: return new EnglishLabels();
        }
    }

    String welcomeMessage();
    String speedSliderTitle();
    String startButtonText();
    String resetButtonText();
    String moreWorldsButtonText();

    String karelCodeErrorTitle();
    String karelCodeErrorMessage();
    String noBeepersErrorMessage();
    String wallCollisionErrorMessage();

    String textFieldReset();
    String textFieldRunning();
    String textFieldSuccess();
    String textFieldError();
}
