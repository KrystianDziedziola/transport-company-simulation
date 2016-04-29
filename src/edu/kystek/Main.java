package edu.kystek;

import edu.kystek.controller.AirportViewController;

public class Main {

    public static void main(String[] args) {

        AirportViewController airportViewController = new AirportViewController();
        airportViewController.show();

        airportViewController.addPlane("Plane-1");
        airportViewController.movePlane("Plane-1", 600, 200);
    }
}
