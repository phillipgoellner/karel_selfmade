package com.pgoellner.karel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class UiBuilder {
    private UiBuilder() {
    }

    static void createWindow(Karel karel) {
        JFrame window = new JFrame();
        karel.registerUpdateCallback(window);

        JPanel mainPanel = new JPanel();
        BorderLayout layout = new BorderLayout();

        KarelWorld canvas = new KarelWorld(karel);
        layout.addLayoutComponent(canvas, BorderLayout.CENTER);
        mainPanel.add(canvas);

        JPanel controls = new JPanel();
        JButton play = new JButton("Play");
        play.addActionListener(new PlayButtonListener(karel));
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
    private boolean hasRun;

    PlayButtonListener(Karel program) {
        this.program = program;
        this.hasRun = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!hasRun) {
            program.run();
            hasRun = true;
        }
    }
}
