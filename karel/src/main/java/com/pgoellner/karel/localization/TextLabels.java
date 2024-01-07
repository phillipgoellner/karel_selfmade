package com.pgoellner.karel.localization;

import java.util.Locale;

public interface TextLabels {
    static TextLabels systemDefaults() {
        switch (Locale.getDefault().getLanguage()) {
            case "de": return new GermanLabels();
            default: return new EnglishLabels();
        }
    }

    String welcomeMessage();
    String startButtonText();
    String resetButtonText();

    String karelCodeErrorTitle();
    String karelCodeErrorMessage();

    String textFieldReset();
    String textFieldRunning();
    String textFieldSuccess();
    String textFieldError();
}
