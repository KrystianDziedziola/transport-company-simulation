package edu.kystek.pwir.view;

import javax.swing.*;
import java.awt.*;

public class AirportView extends JFrame {

    private static int WIDTH = 800, HEIGHT = 600;

    private Dimension windowDimension = new Dimension(WIDTH, HEIGHT);

    private JPanel panel = new JPanel();

    public AirportView() {
        setupFrame();
        addPanel();
    }

    public void showWindow() {
        setVisible(true);
    }

    public void addPlane() {

    }

    private void setupFrame() {
        setTitle("Plane transport simulation - airport");
        setLayout(null);
        setSize(windowDimension);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void addPanel() {
        panel.setSize(windowDimension);
        add(panel);
    }

}
