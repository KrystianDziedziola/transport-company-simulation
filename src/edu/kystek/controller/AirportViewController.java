package edu.kystek.controller;

import edu.kystek.model.Plane;
import edu.kystek.view.AirportView;

public class AirportViewController {

    private AirportView airportView;

    public AirportViewController(AirportView airportView) {
        this.airportView = airportView;
    }

    public void show() {
        airportView.showWindow();
    }

    public void addPlane(String name) {
        airportView.addPlane(new Plane(name));
    }

}
