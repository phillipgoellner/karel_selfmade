package com.pgoellner.karel.localization;

public final class EnglishLabels implements TextLabels {
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
        return "%s!\nOriginated in %s line %d";
    }

    @Override
    public String noBeepersErrorMessage() {
        return "No Beeper to pick up";
    }

    @Override
    public String wallCollisionErrorMessage() {
        return "Oh no, a wall";
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
