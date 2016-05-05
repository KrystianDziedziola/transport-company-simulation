package edu.kystek.controller;

import edu.kystek.controller.helper.Pause;
import edu.kystek.controller.helper.Steps;
import edu.kystek.model.MyPackage;
import edu.kystek.model.Plane;
import edu.kystek.view.AirportView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class AirportController {

    static final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 600;
    private static final int BASE_X = 640, BASE_Y = 0;
    private static final int TIME_TO_WAIT_WHEN_FLIGHT_ENDS = 1000;
    private static final int FUEL_TO_BURN_EACH_STEP = 2;

    private Point baseLocation = new Point(BASE_X, BASE_Y);

    private List<Plane> planes = new ArrayList<>();
    private List<MyPackage> packages = new ArrayList<>();
    private AirportView airportView;

    AirportController() {
        Dimension windowSize = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
        airportView = new AirportView(windowSize);
    }

    void showAirport() {
        airportView.showWindow();
    }

    void addFlight(String planeName, Point sourceLocation, String packageName, int fuelTankCapacity) {
        new Flight(planeName, sourceLocation, packageName, fuelTankCapacity).start();
    }

    private void addPlane(String name, Point location, int fuelTankCapacity) {
        Plane plane = new Plane(name, fuelTankCapacity);
        planes.add(plane);
        airportView.addPlane(plane, location);
    }

    private void movePlaneToBase(String planeName) {
        Plane plane = findPlane(planeName);
        animateMove(plane);
        Pause.pause(TIME_TO_WAIT_WHEN_FLIGHT_ENDS);
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

    private void animateMove(Plane plane) {
        JComponent planeLabel = plane.get();
        Point currentLocation = planeLabel.getLocation();
        Steps steps = new Steps(currentLocation, baseLocation);
        move(plane, currentLocation, steps);
    }

    private void move(Plane plane, Point currentLocation, Steps steps) {
        while (!isTargetReached(currentLocation, baseLocation)) {
            Pause.pause(Steps.TIME_BETWEEN_STEPS);
            changeLocation(currentLocation, steps.getX(), steps.getY());
            airportView.movePlane(plane.get(), currentLocation);
            plane.burnFuel(FUEL_TO_BURN_EACH_STEP);
            if(plane.isTankEmpty()) {
                plane.explode();
                break;
            }
        }
    }

    private void changeLocation(Point currentLocation, int stepX, int stepY) {
        if (currentLocation.getX() < baseLocation.getX()) {
            currentLocation.setLocation(currentLocation.getX() + stepX, currentLocation.getY());
        }
        if (currentLocation.getY() > baseLocation.getY()) {
            currentLocation.setLocation(currentLocation.getX(), currentLocation.getY() - stepY);
        }
    }

    private boolean isTargetReached(Point current, Point target) {
        return (current.getX() >= target.getX()) && (current.getY() <= target.getY());
    }

    private class Flight extends Thread {

        private String planeName;
        private Point sourceLocation;
        private String packageName;
        private int fuelTankCapacity;

        private Flight(String planeName, Point sourceLocation, String packageName, int fuelTankCapacity) {
            this.planeName = planeName;
            this.sourceLocation = sourceLocation;
            this.packageName = packageName;
            this.fuelTankCapacity = fuelTankCapacity;
        }

        @Override
        public void run() {
            addPackage(packageName, sourceLocation);
            Pause.pause(1000);
            addPlane(planeName, sourceLocation, fuelTankCapacity);
            Pause.pause(500);
            removePackage(packageName);
            movePlaneToBase(planeName);
            removePlane(planeName);
        }
    }

    private void removePackage(String name) {
        MyPackage myPackage = findPackage(name);
        myPackage.getLabel().setVisible(false);
    }

    private void addPackage(String packageName, Point sourceLocation) {
        MyPackage myPackage = new MyPackage(packageName);
        packages.add(myPackage);
        airportView.addPackage(myPackage, sourceLocation);
    }

    private MyPackage findPackage(String name) {
        for(MyPackage myPackage : packages) {
            if(myPackage.getName().equals(name)) {
                return myPackage;
            }
        }
        throw new IllegalArgumentException(String.format("Plane '%s' not found.", name));
    }

}
