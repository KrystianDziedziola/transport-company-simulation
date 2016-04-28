package edu.kystek;

import edu.kystek.controller.AirportViewController;
import edu.kystek.view.AirportView;

public class Main {

    public static void main(String[] args) {

        AirportView airportView = new AirportView();
        AirportViewController airportViewController = new AirportViewController(airportView);

        airportViewController.addPlane("Plane-1");
        airportViewController.show();


    }
}
