package edu.kystek.controller;

import edu.kystek.controller.helper.Pause;
import edu.kystek.model.Plane;
import edu.kystek.view.AirportView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AirportController {

    static final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 600;
    private static final int BASE_X = 690, BASE_Y = 0;
    private static final int TIME_TO_WAIT_IN_BASE = 500;

    private List<Plane> planes = new ArrayList<>();

    private AirportView airportView;

    AirportController() {
        Dimension windowSize = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
        Point baseLocation = new Point(BASE_X, BASE_Y);
        airportView = new AirportView(windowSize, baseLocation);
    }

    void showAirport() {
        airportView.showWindow();
    }

    void addFlight(String planeName, Point sourceLocation) {
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
