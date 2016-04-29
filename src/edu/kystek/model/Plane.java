package edu.kystek.model;

import edu.kystek.controller.helper.ImageLabelHelper;

import javax.swing.*;

public class Plane {

    private static final int WIDTH = 100, HEIGHT = 100;
    private String pictureName = "plane.png";

    private String name;
    private JLabel pictureLabel;

    public Plane(String name) {
        this.name = name;
        pictureLabel = new ImageLabelHelper().getLabelWithImage(pictureName, WIDTH, HEIGHT);
    }

    public String getName() {
        return name;
    }

    public JLabel getPictureLabel() {
        return pictureLabel;
    }
}
