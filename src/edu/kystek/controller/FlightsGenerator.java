package edu.kystek.controller;

import edu.kystek.controller.helper.Pause;

import java.awt.*;
import java.util.Random;

public class FlightsGenerator extends Thread {

    private static final int FIRST_PLANE_NUMBER = 1;
    private static final int TIME_BETWEEN_FLIGHTS = 1000;

    private int numberOfPlanes;


    public FlightsGenerator(int numberOfPlanes) {
        this.numberOfPlanes = numberOfPlanes;
    }

    @Override
    public void run() {
        AirportController airportController = new AirportController();
        airportController.showAirport();
        generateFlights(airportController);
    }

    private void generateFlights(AirportController airportController) {
        for(int planeNumber = FIRST_PLANE_NUMBER; planeNumber <= numberOfPlanes; planeNumber++) {
            String name = String.format("Plane-%d", planeNumber);
            Point sourceLocation = generateSourceLocation();
            airportController.addFlight(name, sourceLocation);
            Pause.pause(TIME_BETWEEN_FLIGHTS);
        }
    }

    private Point generateSourceLocation() {
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
