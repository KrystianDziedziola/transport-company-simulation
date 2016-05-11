package edu.kystek.controller;

import edu.kystek.controller.helper.*;
import edu.kystek.model.AirportPackage;
import edu.kystek.model.Plane;
import edu.kystek.view.AirportView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static edu.kystek.controller.helper.FlightDirection.*;

class AirportController {

    private Point baseLocation = new Point(AirportConstants.BASE_X, AirportConstants.BASE_Y);

    private List<Plane> planes = new ArrayList<>();
    private List<AirportPackage> packages = new ArrayList<>();
    private AirportView airportView;

    private boolean isBaseEmpty = true;

    AirportController() {
        Dimension windowSize = new Dimension(AirportConstants.WINDOW_WIDTH, AirportConstants.WINDOW_HEIGHT);
        airportView = new AirportView(windowSize);
    }

    void showAirport() {
        airportView.showWindow();
    }

    void addFlight(Plane plane, String packageName, Point packageLocation) {
        new Flight(plane, packageName, packageLocation).start();
    }

    private void addPlane(Plane plane, Point location) throws InterruptedException {
        planes.add(plane);
        airportView.addPlane(plane, location);
    }

    private void removePlane(Plane plane) {
        airportView.removeLabel(plane.get());
        isBaseEmpty = true;
    }

    private void animateMove(Plane plane, Point targetLocation, FlightDirection flightDirection) {
        JComponent planeLabel = plane.get();
        Point currentLocation = planeLabel.getLocation();
        Steps steps = new Steps(currentLocation, targetLocation);
        move(plane, currentLocation, targetLocation, steps, flightDirection);
        Pause.pause(AirportConstants.TIME_TO_WAIT_WHEN_FLIGHT_ENDS);
    }

    private void move(Plane plane, Point currentLocation, Point targetLocation,
                      Steps steps, FlightDirection flightDirection) {
        while (!isTargetReached(currentLocation, targetLocation, flightDirection)) {
            try {
                moveOneStep(plane, currentLocation, targetLocation, steps, flightDirection);
                if (isInBaseZone(currentLocation) && flightDirection == FlightDirection.TO_BASE) {
                    tryMoveToBase(plane, currentLocation, targetLocation, steps, flightDirection);
                }
            } catch (EmptyTankException e) {
                plane.explode();
                isBaseEmpty = true;
                return;
            }
        }
    }

    private void tryMoveToBase(Plane plane, Point currentLocation, Point targetLocation, Steps steps, FlightDirection flightDirection) throws EmptyTankException {
        while (!isBaseEmpty) {
            plane.burnFuel(AirportConstants.FUEL_TO_BURN_EACH_STEP);
            Pause.pause(Steps.TIME_BETWEEN_STEPS);
        }
        isBaseEmpty = false;
        while (!isTargetReached(currentLocation, targetLocation, flightDirection)) {
            moveOneStep(plane, currentLocation, targetLocation, steps, flightDirection);
        }
    }

    private void moveOneStep(Plane plane, Point currentLocation, Point targetLocation,
                             Steps steps, FlightDirection flightDirection) throws EmptyTankException {
        changeLocation(currentLocation, targetLocation, steps, flightDirection);
        airportView.movePlane(plane, currentLocation);
        plane.burnFuel(AirportConstants.FUEL_TO_BURN_EACH_STEP);
        Pause.pause(Steps.TIME_BETWEEN_STEPS);
    }

    private boolean isInBaseZone(Point currentLocation) {
        return currentLocation.getX() > AirportConstants.BASE_ZONE_X &&
                currentLocation.getY() < AirportConstants.BASE_ZONE_Y;
    }

    private void changeLocation(Point currentLocation, Point targetLocation,
                                Steps steps, FlightDirection flightDirection) {
        switch (flightDirection) {
            case TO_BASE:
                changeToBaseLocation(currentLocation, targetLocation, steps);
                break;
            case FROM_BASE:
                changeFromBaseLocation(currentLocation, targetLocation, steps);
                break;
            default:
                throw new IllegalArgumentException("Wrong flight direction.");
        }

    }

    private void changeToBaseLocation(Point currentLocation, Point targetLocation, Steps steps) {
        if (currentLocation.getX() < targetLocation.getX()) {
            currentLocation.setLocation(currentLocation.getX() + steps.getX(), currentLocation.getY());
        }
        if (currentLocation.getY() > targetLocation.getY()) {
            currentLocation.setLocation(currentLocation.getX(), currentLocation.getY() - steps.getY());
        }
    }

    private void changeFromBaseLocation(Point currentLocation, Point targetLocation, Steps steps) {
        if (currentLocation.getX() > targetLocation.getX()) {
            currentLocation.setLocation(currentLocation.getX() - steps.getX(), currentLocation.getY());
        }
        if (currentLocation.getY() < targetLocation.getY()) {
            currentLocation.setLocation(currentLocation.getX(), currentLocation.getY() + steps.getY());
        }
    }

    private boolean isTargetReached(Point current, Point target, FlightDirection flightDirection) {
        boolean isReached;
        switch (flightDirection) {
            case TO_BASE:
                isReached = (current.getX() >= target.getX()) && (current.getY() <= target.getY());
                break;
            case FROM_BASE:
                isReached = (current.getX() <= target.getX()) && (current.getY() >= target.getY());
                break;
            default:
                throw new IllegalArgumentException("Wrong flight direction.");
        }
        return isReached;
    }

    private class Flight extends Thread {

        private Plane plane;
        private Point packageLocation;
        private String packageName;

        private Flight(Plane plane, String packageName, Point packageLocation) {
            this.plane = plane;
            this.packageLocation = packageLocation;
            this.packageName = packageName;
        }

        @Override
        public void run() {
            //TODO: add constants
            try {
                addPackage(packageName, packageLocation);
                Pause.pause(1000);
                addPlane(plane, baseLocation);
                Pause.pause(500);
                animateMove(plane, packageLocation, FROM_BASE);
                plane.flip();
                removePackage(packageName);
                Pause.pause(500);
                animateMove(plane, baseLocation, TO_BASE);
                removePlane(plane);
            } catch (InterruptedException e) {
                    e.printStackTrace();
            }
        }
    }

    private void removePackage(String name) {
        AirportPackage airportPackage = findPackage(name);
        airportPackage.getLabel().setVisible(false);
    }

    private void addPackage(String packageName, Point sourceLocation) {
        AirportPackage airportPackage = new AirportPackage(packageName);
        packages.add(airportPackage);
        airportView.addPackage(airportPackage, sourceLocation);
    }

    private AirportPackage findPackage(String name) {
        for(AirportPackage airportPackage : packages) {
            if(airportPackage.getName().equals(name)) {
                return airportPackage;
            }
        }
        throw new IllegalArgumentException(String.format("Plane '%s' not found.", name));
    }

}
