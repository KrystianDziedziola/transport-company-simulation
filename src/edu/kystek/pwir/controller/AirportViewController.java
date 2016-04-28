package edu.kystek.pwir.controller;

import edu.kystek.pwir.view.AirportView;

public class AirportViewController {

    private AirportView airportView;

    public AirportViewController(AirportView airportView) {
        this.airportView = airportView;
    }

    public void show() {
        airportView.showWindow();
    }

}
