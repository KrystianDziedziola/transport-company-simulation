package edu.kystek;

import edu.kystek.controller.AirportController;

public class Main {

    public static void main(String[] args) {

        AirportController airportController = new AirportController();
        airportController.show();

        airportController.addFlight("Plane-1", 0, 500);
        airportController.addFlight("Plane-2", 400, 400);
        airportController.addFlight("Plane-3", 0, 100);
    }

}
