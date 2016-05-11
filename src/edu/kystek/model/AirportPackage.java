package edu.kystek.model;

import edu.kystek.controller.helper.AirportConstants;
import edu.kystek.controller.helper.ImageHelper;

import javax.swing.*;

public class AirportPackage {

    private String name;

    private JLabel packageLabel;

    public AirportPackage(String name) {
        this.name = name;
        packageLabel = new ImageHelper().getLabelWithImage("package.png",
                AirportConstants.PACKAGE_WIDTH, AirportConstants.PACKAGE_HEIGHT);
    }

    public JLabel getLabel() {
        return packageLabel;
    }

    public String getName() {
        return name;
    }
}
