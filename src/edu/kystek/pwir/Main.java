package edu.kystek.pwir;

import edu.kystek.pwir.controller.AirportViewController;
import edu.kystek.pwir.view.AirportView;

public class Main {

    public static void main(String[] args) {

        AirportView airportView = new AirportView();
        AirportViewController airportViewController = new AirportViewController(airportView);

        airportViewController.show();
    }
}
