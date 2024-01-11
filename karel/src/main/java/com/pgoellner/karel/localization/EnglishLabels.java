package com.pgoellner.karel.localization;

public class EnglishLabels implements TextLabels {
    @Override
    public String welcomeMessage() {
        return "Welcome to Karel!";
    }

    @Override
    public String speedSliderTitle() {
        return "  Karel's Speed";
    }

    @Override
    public String startButtonText() {
        return "Start Program";
    }

    @Override
    public String resetButtonText() {
        return "Reset Program";
    }

    @Override
    public String moreWorldsButtonText() {
        return "Load Other Worlds";
    }

    @Override
    public String karelCodeErrorTitle() {
        return "Error D:";
    }

    @Override
    public String karelCodeErrorMessage() {
        return "Error!\nOriginated in %s line %d";
    }

    @Override
    public String textFieldReset() {
        return "Program reset";
    }

    @Override
    public String textFieldRunning() {
        return "Running...";
    }

    @Override
    public String textFieldSuccess() {
        return "Finished!";
    }

    @Override
    public String textFieldError() {
        return "Error :(";
    }
}
