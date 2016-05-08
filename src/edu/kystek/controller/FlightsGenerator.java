
package edu.kystek.controller;

import edu.kystek.controller.helper.Pause;
import edu.kystek.model.AirportPackage;
import edu.kystek.model.Plane;

import java.awt.*;
import java.util.Random;

public class FlightsGenerator extends Thread {

    private static final int FIRST_PLANE_NUMBER = 1;
    private static final int TIME_BETWEEN_FLIGHTS = 3500;
    private static final double TANK_DIFFERENCE_PERCENTAGE = 0.25;

    private int numberOfPlanes;
    private int averageFuelTankCapacity;

    private AirportController airportController = new AirportController();

    public FlightsGenerator(int numberOfPlanes, int averageFuelTankCapacity) {
        this.numberOfPlanes = numberOfPlanes;
        this.averageFuelTankCapacity = averageFuelTankCapacity;
    }

    @Override
    public void run() {
        airportController.showAirport();
        generateFlights(airportController);
    }

    private void generateFlights(AirportController airportController) {
        for(int planeNumber = FIRST_PLANE_NUMBER; planeNumber <= numberOfPlanes; planeNumber++) {
            Plane plane = createPlane(planeNumber);

            String packageName = String.format("AirportPackage-%d", planeNumber);
            Point packageLocation = generatePackageLocation();

            airportController.addFlight(plane, packageName, packageLocation);
            Pause.pause(TIME_BETWEEN_FLIGHTS);
        }
    }

    private Plane createPlane(int planeNumber) {
        String name = String.format("Plane-%d", planeNumber);
        int fuelTankCapacity = generateFuelTankCapacity();
        return new Plane(name, fuelTankCapacity);
    }

    private int generateFuelTankCapacity() {
        Random random = new Random();
        int difference = averageFuelTankCapacity / 2;
        int rangeFrom = (int) (averageFuelTankCapacity - averageFuelTankCapacity * TANK_DIFFERENCE_PERCENTAGE);
        return random.nextInt(difference) + rangeFrom;
    }

    private Point generatePackageLocation() {
        final int MARGIN = 100;
        final int DISTANCE_FROM_BASE = 300;
        final int X_RANGE = AirportController.WINDOW_WIDTH - DISTANCE_FROM_BASE;
        final int Y_RANGE = AirportController.WINDOW_HEIGHT - DISTANCE_FROM_BASE;

        Random random = new Random();
        int sourceX = random.nextInt(X_RANGE) + MARGIN;
        int sourceY = random.nextInt(Y_RANGE) + MARGIN;
        return new Point(sourceX, sourceY);
    }
}
