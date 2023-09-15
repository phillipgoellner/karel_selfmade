package com.pgoellner.karel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class UiBuilder {
    private UiBuilder() {
    }

    static void createWindow(Karel karel, World world, String programTitle) {
        JFrame window = new JFrame(programTitle);

        JPanel mainPanel = new JPanel();
        BorderLayout layout = new BorderLayout();

        KarelCanvas canvas = new KarelCanvas(karel, world);
        layout.addLayoutComponent(canvas, BorderLayout.CENTER);
        mainPanel.add(canvas);

        JPanel controls = new JPanel();

        KarelTextField text = new KarelTextField("Welcome to Karel!");
        controls.add(text);

        JButton play = new JButton("Start Program");
        play.addActionListener(new KarelButtonListener(karel, window, text));
        controls.add(play);
        layout.addLayoutComponent(controls, BorderLayout.WEST);
        mainPanel.add(controls);

        mainPanel.setLayout(layout);

        window.add(mainPanel);
        window.setMinimumSize(new Dimension(900, 600));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}

class KarelButtonListener implements ActionListener {
    private final Karel program;
    private final JFrame window;
    private final KarelTextField textField;
    private boolean hasRun;

    KarelButtonListener(Karel program, JFrame window, KarelTextField textField) {
        this.program = program;
        this.window = window;
        this.textField = textField;
        this.hasRun = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startProgram();
    }

    private void startProgram() {
        if (!hasRun) {
            Thread mainLoop = new Thread(() -> {
                try {
                    textField.displayRunning();
                    program.run();
                    textField.displaySucceeded();
                } catch (WallCollision collision) {
                    textField.displayError();
                }
            });

            Thread rendering = new Thread(() -> {
                while (true) {
                    window.repaint();
                    try {
                        Thread.sleep(1000L / 140);
                    } catch (InterruptedException interruptedException) {
                        System.err.printf("Something really bad went wrong: %s%n", interruptedException);
                        System.err.println("Please get mad at the developer");
                    }
                }
            });

            mainLoop.start();
            rendering.start();

            hasRun = true;
        }
    }
}
