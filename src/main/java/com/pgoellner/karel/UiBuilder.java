package com.pgoellner.karel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class UiBuilder {
    private UiBuilder() {
    }

    static void createWindow(Karel karel, World world) {
        JFrame window = new JFrame();
        karel.registerUpdateCallback(window);

        JPanel mainPanel = new JPanel();
        BorderLayout layout = new BorderLayout();

        KarelCanvas canvas = new KarelCanvas(karel, world);
        layout.addLayoutComponent(canvas, BorderLayout.CENTER);
        mainPanel.add(canvas);

        JPanel controls = new JPanel();
        JButton play = new JButton("Play");
        play.addActionListener(new PlayButtonListener(karel, window));
        controls.add(play);
        layout.addLayoutComponent(controls, BorderLayout.WEST);
        mainPanel.add(controls);

        mainPanel.setLayout(layout);

        window.add(mainPanel);
        window.setMinimumSize(new Dimension(700, 700));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}

class PlayButtonListener implements ActionListener {
    private final Karel program;
    private final JFrame window;
    private boolean hasRun;

    PlayButtonListener(Karel program, JFrame window) {
        this.program = program;
        this.window = window;
        this.hasRun = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!hasRun) {
            new Thread(program::run).start();
            new Thread(() -> {
                while (true) {
                    window.repaint();
                    try {
                        Thread.sleep(1000L / 140);
                    } catch (InterruptedException interruptedException) {
                        System.err.printf("Something really bad went wrong: %s%n", interruptedException);
                        System.err.println("Please get mad at the developer");
                    }
                }
            }).start();

            hasRun = true;
        }
    }
}
