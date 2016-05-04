package edu.kystek.view;

import edu.kystek.model.Plane;

import javax.swing.*;
import java.awt.*;

public class AirportView extends JFrame {

    private Dimension windowSize;

    public AirportView(Dimension windowSize) {
        this.windowSize = windowSize;
        setupFrame();
    }

    public void showWindow() {
        setVisible(true);
    }

    public void addPlane(Plane plane, Point location) {
        JComponent planeComponent = plane.get();
        planeComponent.setLocation(location);
        add(planeComponent);
        refresh();
    }

    public void movePlane(JComponent planeLabel, Point point) {
        planeLabel.setLocation(point);
        refresh();
    }

    public void removeLabel(JComponent label) {
        label.setVisible(false);
        refresh();
    }

    private void setupFrame() {
        setTitle("Plane transport simulation - airport");
        setLayout(null);
        setSize(windowSize);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void refresh() {
        revalidate();
        repaint();
    }



}
