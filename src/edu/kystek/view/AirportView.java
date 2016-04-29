package edu.kystek.view;

import edu.kystek.controller.helper.ImageLabelHelper;
import edu.kystek.controller.helper.Pause;
import edu.kystek.model.Plane;

import javax.swing.*;
import java.awt.*;

public class AirportView extends JFrame {

    private static int WIDTH = 800, HEIGHT = 600;
    private static int PLANE_X = 20, PLANE_Y = HEIGHT - 130;

    private Dimension windowDimension = new Dimension(WIDTH, HEIGHT);
    private Point defaultPlaneLocation = new Point(PLANE_X, PLANE_Y);

    public AirportView() {
        setupFrame();
    }

    public void showWindow() {
        setVisible(true);
    }

    public void addPlane(Plane plane) {
        JLabel planePictureLabel = plane.getPictureLabel();
        planePictureLabel.setLocation(defaultPlaneLocation);
        add(planePictureLabel);
        refresh();
    }

    public void animateMove(JLabel planeLabel, Point targetLocation) {
        Point currentLocation = planeLabel.getLocation();
        Steps steps = new Steps(currentLocation, targetLocation);
        int stepX = steps.getStepX();
        int stepY = steps.getStepY();

        while (!isTargetReached(currentLocation, targetLocation)) {
            Pause.pause(Steps.TIME_BETWEEN_STEPS);
            if (currentLocation.getX() < targetLocation.getX()) {
                currentLocation.setLocation(currentLocation.getX() + stepX, currentLocation.getY());
            }
            if (currentLocation.getY() > targetLocation.getY()) {
                currentLocation.setLocation(currentLocation.getX(), currentLocation.getY() - stepY);
            }
            movePlane(planeLabel, currentLocation);
        }
    }

    public void movePlane(JLabel planeLabel, Point point) {
        planeLabel.setLocation(point);
        refresh();
    }

    private boolean isTargetReached(Point current, Point target) {
        return (current.getX() > target.getX()) && (current.getY() < target.getY());
    }

    private void setupFrame() {
        setTitle("Plane transport simulation - airport");
        setLayout(null);
        setSize(windowDimension);
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
        private static final int NUMBER_OF_STEPS = 100;
        private static final int TIME_BETWEEN_STEPS = 100;

        Steps(Point current, Point target) {
            this.current = current;
            this.target = target;
        }

        int getStepX() {
            return (int) ((target.getX() - current.getX()) / NUMBER_OF_STEPS);
        }

        int getStepY() {
            return (int) ((current.getY() - target.getY()) / NUMBER_OF_STEPS);
        }

    }

}
