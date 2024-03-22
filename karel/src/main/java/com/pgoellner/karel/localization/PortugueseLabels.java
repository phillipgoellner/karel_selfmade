package com.pgoellner.karel.localization;

public final class PortugueseLabels implements TextLabels {
    @Override
    public String welcomeMessage() {
        return "Bem vindo a Karel!";
    }

    @Override
    public String speedSliderTitle() {
        return "  velocidade de Karel";
    }

    @Override
    public String startButtonText() {
        return "Iniciar o programa";
    }

    @Override
    public String resetButtonText() {
        return "Programm zurücksetzen";
    }

    @Override
    public String moreWorldsButtonText() {
        return "Outros mundos";
    }

    @Override
    public String karelCodeErrorTitle() {
        return "Erro D:";
    }

    @Override
    public String karelCodeErrorMessage() {
        return "Erro!\nProvavelmente em %s na linha %d";
    }

    @Override
    public String noBeepersErrorMessage() {
        return "Nao tem Beeper pra collecionar";
    }

    @Override
    public String wallCollisionErrorMessage() {
        return "Xiii, uma parede";
    }

    @Override
    public String textFieldReset() {
        return "Restaurar o programa";
    }

    @Override
    public String textFieldRunning() {
        return "Rodando...";
    }

    @Override
    public String textFieldSuccess() {
        return "Pronto!";
    }

    @Override
    public String textFieldError() {
        return "Erro :(";
    }
}
