package com.pgoellner.karel;

import com.pgoellner.karel.errors.CouldNotGetLookAndFeel;
import com.pgoellner.karel.errors.WallCollision;
import com.pgoellner.karel.localization.TextLabels;
import com.pgoellner.karel.parse.WorldFileParser;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

final class UiBuilder {
    static final TextLabels labels = TextLabels.systemDefaults();

    private static KarelCanvas currentCanvas;

    private UiBuilder() {
    }

    static void createWindow(Karel karel, World world, String programTitle, KarelSpeedSetting speedSetting) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new CouldNotGetLookAndFeel(e);
        }

        JFrame window = new JFrame(programTitle);

        JPanel mainPanel = new JPanel();
        BorderLayout layout = new BorderLayout();

        currentCanvas = new KarelCanvas(karel, world);
        layout.addLayoutComponent(currentCanvas, BorderLayout.CENTER);
        mainPanel.add(currentCanvas);

        JPanel controls = createControls(karel, world, window, speedSetting);

        layout.addLayoutComponent(controls, BorderLayout.WEST);
        mainPanel.add(controls);

        mainPanel.setLayout(layout);

        window.add(mainPanel);
        window.setMinimumSize(new Dimension(900, 600));
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    private static JPanel createControls(Karel karel, World world, JFrame window, KarelSpeedSetting speedSetting) {
        JPanel controls = new JPanel();
        GridLayout gridLayout = new GridLayout(10, 1);
        gridLayout.setVgap(10);
        controls.setLayout(gridLayout);

        KarelTextField text = new KarelTextField(labels.welcomeMessage());
        controls.add(text);
        controls.add(new JLabel(""));

        JLabel speedTitle = new JLabel(labels.speedSliderTitle());
        JSlider speedSlider = new JSlider(SwingConstants.HORIZONTAL,0, 10, 5);
        speedSlider.setSnapToTicks(true);
        speedSlider.setMajorTickSpacing(5);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(new KarelSliderListener(speedSetting));
        controls.add(speedTitle);
        controls.add(speedSlider);
        controls.add(new JLabel(""));

        KarelButtonListener buttonListener = new KarelButtonListener(karel, world, window, text);

        JButton play = new JButton(labels.startButtonText());
        play.addActionListener(buttonListener);
        controls.add(play);

        JButton reset = new JButton(labels.resetButtonText());
        reset.addActionListener(buttonListener);
        controls.add(reset);

        JButton load = new JButton(labels.moreWorldsButtonText());
        load.addActionListener(buttonListener);
        controls.add(load);
        return controls;
    }

    static void newWorld(World newOne) {
        currentCanvas.newWorld(newOne);
    }
}

class KarelSliderListener implements ChangeListener {
    private final KarelSpeedSetting speedSetting;
    public KarelSliderListener(KarelSpeedSetting speedSetting) {
        this.speedSetting = speedSetting;
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            speedSetting.newValue(source.getValue());
        }
    }
}

class KarelButtonListener implements ActionListener {
    private final Karel program;
    private World world;
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
            String text = source.getText();
            if (text.equals(UiBuilder.labels.startButtonText())) {
                startProgram();
            } else if (text.equals(UiBuilder.labels.resetButtonText())) {
                resetProgram();
            } else if (text.equals(UiBuilder.labels.moreWorldsButtonText())) {
                selectNewWorld();
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

        JOptionPane.showMessageDialog(
                window,
                String.format(UiBuilder.labels.karelCodeErrorMessage(), errorClass, errorLineNumber),
                UiBuilder.labels.karelCodeErrorTitle(),
                JOptionPane.ERROR_MESSAGE);
    }

    private void resetProgram() {
        if (!running) {
            program.resetToStartingPosition();
            world.resetToOriginalState();
            textField.reset();
        }
    }

    private void selectNewWorld() {
        JFileChooser fc = new JFileChooser("./worlds");
        int returnVal = fc.showOpenDialog(window);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            WorldFileParser parser = new WorldFileParser(fc.getSelectedFile().getAbsolutePath());
            World newWorld = parser.fromDescription();
            this.world = newWorld;
            Karel.setGameState(program, newWorld, parser);
            UiBuilder.newWorld(newWorld);
            window.repaint();
        }
    }
}
