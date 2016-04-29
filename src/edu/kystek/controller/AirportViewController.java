package edu.kystek.controller;

import edu.kystek.model.Plane;
import edu.kystek.view.AirportView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AirportViewController {

    private List<Plane> planes = new ArrayList<>();

    private AirportView airportView = new AirportView();

    public void show() {
        airportView.showWindow();
    }

    public void addPlane(String name) {
        Plane plane = new Plane(name);
        planes.add(plane);
        airportView.addPlane(plane);
    }

    public void movePlane(String planeName, int x, int y) {
        Plane plane = findPlane(planeName);
        JLabel planeLabel = plane.getPictureLabel();
        Point targetPoint = new Point(x, y);
        airportView.animateMove(planeLabel, targetPoint);
    }

    private Plane findPlane(String name) {
        for(Plane plane : planes) {
            if(plane.getName().equals(name)) {
                return plane;
            }
        }
        throw new IllegalArgumentException(String.format("Plane '%s' not found.", name));
    }

}
