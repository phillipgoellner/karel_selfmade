package com.pgoellner.karel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

final class UiBuilder {
    private UiBuilder() {
    }

    static void createWindow(Karel karel, World world, String programTitle) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new CouldNotGetLookAndFeel(e);
        }

        JFrame window = new JFrame(programTitle);

        JPanel mainPanel = new JPanel();
        BorderLayout layout = new BorderLayout();

        KarelCanvas canvas = new KarelCanvas(karel, world);
        layout.addLayoutComponent(canvas, BorderLayout.CENTER);
        mainPanel.add(canvas);

        JPanel controls = createControls(karel, world, window);

        layout.addLayoutComponent(controls, BorderLayout.WEST);
        mainPanel.add(controls);

        mainPanel.setLayout(layout);

        window.add(mainPanel);
        window.setMinimumSize(new Dimension(900, 600));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    private static JPanel createControls(Karel karel, World world, JFrame window) {
        JPanel controls = new JPanel();
        controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));

        KarelTextField text = new KarelTextField("Welcome to Karel!");
        controls.add(text);

        KarelButtonListener buttonListener = new KarelButtonListener(karel, world, window, text);

        JButton play = new JButton("Start Program");
        play.addActionListener(buttonListener);
        controls.add(play);

        JButton reset = new JButton("Reset Program");
        reset.addActionListener(buttonListener);
        controls.add(reset);
        return controls;
    }
}

class KarelButtonListener implements ActionListener {
    private static final String ERROR_MESSAGE = "Error!\nOriginated in %s line %d";

    private final Karel program;
    private final World world;
    private final JFrame window;
    private final KarelTextField textField;
    private boolean running;

    KarelButtonListener(Karel program, World world, JFrame window, KarelTextField textField) {
        this.program = program;
        this.world = world;
        this.window = window;
        this.textField = textField;
        this.running = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton source = (JButton) e.getSource();
            switch (source.getText()) {
                case "Start Program": startProgram(); break;
                case "Reset Program": resetProgram(); break;
                default: break;
            }
        }
    }

    private void startProgram() {
        if (!running) {
            Thread mainLoop = new Thread(() -> {
                running = true;
                try {
                    textField.displayRunning();
                    program.run();
                    textField.displaySucceeded();
                } catch (WallCollision collision) {
                    display(collision);
                } finally {
                    running = false;
                }
            });

            Thread rendering = new Thread(() -> {
                while (true) {
                    window.repaint();
                    Timer.pause(1000L / 140);
                }
            });

            mainLoop.start();
            rendering.start();
        }
    }

    private void display(RuntimeException error) {
        textField.displayError();

        final StackTraceElement errorOrigin = Arrays
                .stream(error.getStackTrace())
                .filter(
                        stackTraceElement -> {
                            final String className = stackTraceElement.getClassName();
                            return !className.equals("com.pgoellner.karel.KarelButtonListener") &&
                                    !className.equals("com.pgoellner.karel.Karel") &&
                                    !className.equals("java.lang.Thread");
                        }
                )
                .findFirst()
                .orElse(new StackTraceElement("", "", "", -1));

        final String errorClass = errorOrigin.getClassName();
        final int errorLineNumber = errorOrigin.getLineNumber();

        JOptionPane.showMessageDialog(window, String.format(ERROR_MESSAGE, errorClass, errorLineNumber), "Error D:", JOptionPane.ERROR_MESSAGE);
    }

    private void resetProgram() {
        if (!running) {
            program.resetToStartingPosition();
            world.resetToOriginalState();
            textField.reset();
        }
    }
}
