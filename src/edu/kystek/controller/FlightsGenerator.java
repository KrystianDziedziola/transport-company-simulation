
package edu.kystek.controller;

import edu.kystek.controller.helper.AirportConstants;
import edu.kystek.controller.helper.Pause;
import edu.kystek.model.Plane;

import java.awt.*;
import java.util.Random;

public class FlightsGenerator extends Thread {



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
        for(int planeNumber = AirportConstants.FIRST_PLANE_NUMBER; planeNumber <= numberOfPlanes; planeNumber++) {
            Plane plane = createPlane(planeNumber);

            String packageName = String.format("AirportPackage-%d", planeNumber);
            Point packageLocation = generatePackageLocation();

            airportController.addFlight(plane, packageName, packageLocation);
            Pause.pause(AirportConstants.TIME_BETWEEN_FLIGHTS);
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
        int rangeFrom = (int) (averageFuelTankCapacity - averageFuelTankCapacity *
                AirportConstants.TANK_DIFFERENCE_PERCENTAGE);
        return random.nextInt(difference) + rangeFrom;
    }

    private Point generatePackageLocation() {
        Random random = new Random();
        //TODO: add constants
        int x = random.nextInt(550) + 50;
        int y = random.nextInt(250) + 200;
        return new Point(x , y);
    }

}
