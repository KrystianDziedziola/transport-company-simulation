package edu.kystek.controller;

import edu.kystek.controller.helper.Pause;
import edu.kystek.model.Plane;
import edu.kystek.view.AirportView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class AirportController {

    static final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 600;
    private static final int BASE_X = 640, BASE_Y = 0;
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

    void addFlight(String planeName, Point sourceLocation, int fuelTankCapacity) {
        new Flight(planeName, sourceLocation, fuelTankCapacity).start();
    }

    private void addPlane(String name, Point location, int fuelTankCapacity) {
        Plane plane = new Plane(name, fuelTankCapacity);
        planes.add(plane);
        airportView.addPlane(plane, location);
    }

    private void movePlaneToBase(String planeName) {
        Plane plane = findPlane(planeName);
        JComponent planeComponent = plane.get();
        airportView.animateMove(planeComponent);
        Pause.pause(TIME_TO_WAIT_IN_BASE);
    }

    private void removePlane(String name) {
        Plane plane = findPlane(name);
        airportView.removeLabel(plane.get());
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

        private String planeName;
        private Point sourceLocation;
        private int fuelTankCapacity;

        private Flight(String planeName, Point sourceLocation, int fuelTankCapacity) {
            this.planeName = planeName;
            this.sourceLocation = sourceLocation;
            this.fuelTankCapacity = fuelTankCapacity;
        }

        @Override
        public void run() {
            addPlane(planeName, sourceLocation, fuelTankCapacity);
            movePlaneToBase(planeName);
            removePlane(planeName);
        }
    }

}
