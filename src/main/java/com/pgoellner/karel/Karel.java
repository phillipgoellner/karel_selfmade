package com.pgoellner.karel;

import javax.swing.*;
import java.awt.*;

public class Karel {
    public static void main(String[] args) {
        createWindow(createMainPanel());
    }

    private static JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        BorderLayout layout = new BorderLayout();

        KarelWorld canvas = new KarelWorld();
        layout.addLayoutComponent(canvas, BorderLayout.CENTER);
        mainPanel.add(canvas);

        JPanel controls = new JPanel();
        controls.add(new JButton("Play"));
        layout.addLayoutComponent(controls, BorderLayout.WEST);
        mainPanel.add(controls);

        mainPanel.setLayout(layout);
        return mainPanel;
    }

    private static void createWindow(JPanel content) {
        JFrame window = new JFrame();

        window.add(content);

        window.setMinimumSize(new Dimension(700, 700));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
