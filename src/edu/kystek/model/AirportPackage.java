package edu.kystek.model;

import edu.kystek.controller.helper.ImageLabelHelper;

import javax.swing.*;

public class AirportPackage {

    private static final int WIDTH = 50, HEIGHT = 50;

    private String name;

    private JLabel packageLabel;

    public AirportPackage(String name) {
        this.name = name;
        packageLabel = new ImageLabelHelper().getLabelWithImage("package.png", WIDTH, HEIGHT);
    }

    public JLabel getLabel() {
        return packageLabel;
    }

    public String getName() {
        return name;
    }
}
