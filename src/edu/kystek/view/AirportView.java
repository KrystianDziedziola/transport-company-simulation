package edu.kystek.view;

import edu.kystek.controller.helper.Pause;
import edu.kystek.model.Plane;

import javax.swing.*;
import java.awt.*;

public class AirportView extends JFrame {

    private Dimension windowSize;
    private Point baseLocation;

    public AirportView(Dimension windowSize, Point baseLocation) {
        this.baseLocation = baseLocation;
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

    public void animateMove(JComponent planeLabel) {
        Point currentLocation = planeLabel.getLocation();
        Steps steps = new Steps(currentLocation, baseLocation);
        int stepX = steps.getStepX();
        int stepY = steps.getStepY();

        while (!isTargetReached(currentLocation, baseLocation)) {
            Pause.pause(Steps.TIME_BETWEEN_STEPS);
            if (currentLocation.getX() < baseLocation.getX()) {
                currentLocation.setLocation(currentLocation.getX() + stepX, currentLocation.getY());
            }
            if (currentLocation.getY() > baseLocation.getY()) {
                currentLocation.setLocation(currentLocation.getX(), currentLocation.getY() - stepY);
            }
            movePlane(planeLabel, currentLocation);
        }
    }

    private void movePlane(JComponent planeLabel, Point point) {
        planeLabel.setLocation(point);
        refresh();
    }

    public void removeLabel(JComponent label) {
        label.setVisible(false);
        refresh();
    }

    private boolean isTargetReached(Point current, Point target) {
        return (current.getX() >= target.getX()) && (current.getY() <= target.getY());
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

    private class Steps {

        private Point target, current;
        private static final int TIME_BETWEEN_STEPS = 50;

        Steps(Point current, Point target) {
            this.current = current;
            this.target = target;
        }

        //FIXME: fix this two functions to get flight in straight line
        int getStepX() {
//            return (int) ((target.getX() - current.getX()) / NUMBER_OF_STEPS);
//            return (int) ((target.getX() - current.getX()) / (current.getY() - target.getY()) * 5);
            return 5;
        }

        int getStepY() {
//            return (int) ((current.getY() - target.getY()) / NUMBER_OF_STEPS);
            return 5;
        }

    }

}
