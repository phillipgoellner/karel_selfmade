package com.pgoellner.karel.localization;

public interface TextLabels {
    static TextLabels systemDefaults() {
        return new EnglishLabels();
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
