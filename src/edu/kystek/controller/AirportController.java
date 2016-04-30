package edu.kystek.controller;

import edu.kystek.controller.helper.Pause;
import edu.kystek.model.Plane;
import edu.kystek.view.AirportView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AirportController {

    private static int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 600;
    private static int BASE_X = 690, BASE_Y = 0;
    private static int TIME_TO_WAIT_IN_BASE = 1000;

    private Dimension windowSize = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    private Point baseLocation = new Point(BASE_X, BASE_Y);

    private List<Plane> planes = new ArrayList<>();

    private AirportView airportView;

    public AirportController() {
        airportView = new AirportView(windowSize, baseLocation);
    }

    public void show() {
        airportView.showWindow();
    }

    public void addFlight(String planeName, int sourceX, int sourceY) {
        Point sourceLocation = new Point(sourceX, sourceY);
        new Flight(planeName, sourceLocation).start();
    }

    private void addPlane(String name, Point location) {
        Plane plane = new Plane(name);
        planes.add(plane);
        airportView.addPlane(plane, location);
    }

    private void movePlaneToBase(String planeName) {
        Plane plane = findPlane(planeName);
        JLabel planeLabel = plane.getPictureLabel();
        airportView.animateMove(planeLabel);
        Pause.pause(TIME_TO_WAIT_IN_BASE);
    }

    private void removePlane(String name) {
        Plane plane = findPlane(name);
        airportView.removeLabel(plane.getPictureLabel());
    }

    private Plane findPlane(String name) {
        for(Plane plane : planes) {
            if(plane.getName().equals(name)) {
                return plane;
            }
        }
        throw new IllegalArgumentException(String.format("Plane '%s' not found.", name));
    }

    private class Flight extends Thread {

        private final String planeName;
        private final Point sourceLocation;

        private Flight(String planeName, Point sourceLocation) {
            this.planeName = planeName;
            this.sourceLocation = sourceLocation;
        }

        @Override
        public void run() {
            addPlane(planeName, sourceLocation);
            movePlaneToBase(planeName);
            removePlane(planeName);
        }
    }

}
