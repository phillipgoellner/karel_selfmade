package com.pgoellner.karel.localization;

public final class GermanLabels implements TextLabels {
    @Override
    public String welcomeMessage() {
        return "Willkommen bei Karel!";
    }

    @Override
    public String speedSliderTitle() {
        return "  Karels Geschwindigkeit";
    }

    @Override
    public String startButtonText() {
        return "Programm starten";
    }

    @Override
    public String resetButtonText() {
        return "Programm zurücksetzen";
    }

    @Override
    public String moreWorldsButtonText() {
        return "Weitere Welten";
    }

    @Override
    public String karelCodeErrorTitle() {
        return "Fehler D:";
    }

    @Override
    public String karelCodeErrorMessage() {
        return "Fehler!\nVermutlich entstanden in %s Zeile %d";
    }

    @Override
    public String noBeepersErrorMessage() {
        return "Keine Beeper zum Aufheben";
    }

    @Override
    public String wallCollisionErrorMessage() {
        return "Oh nein, eine Wand";
    }

    @Override
    public String textFieldReset() {
        return "Programm zurückgesetzt";
    }

    @Override
    public String textFieldRunning() {
        return "Läuft...";
    }

    @Override
    public String textFieldSuccess() {
        return "Fertig!";
    }

    @Override
    public String textFieldError() {
        return "Fehler :(";
    }
}
