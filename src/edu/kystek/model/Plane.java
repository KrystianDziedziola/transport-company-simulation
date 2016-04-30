package edu.kystek.model;

import edu.kystek.controller.helper.ImageLabelHelper;

import javax.swing.*;

public class Plane {

    private static final int PICTURE_WIDTH = 100, PICTURE_HEIGHT = 100;
    private static final int PANEL_WIDTH = 150, PANEL_HEIGHT = 150;

    private String name;

    private JLabel nameLabel;
    private JLabel pictureLabel;
    private JPanel panel = new JPanel();
    private JProgressBar fuelTankBar = new JProgressBar();

    public Plane(String name, int tankCapacity) {
        this.name = name;
        nameLabel = new JLabel(name);
        setupLabels();
        setupTankBar(tankCapacity);
        setupPanel();
        addComponents();
    }

    public String getName() {
        return name;
    }

    public JComponent get() {
        return panel;
    }

    private void setupLabels() {
        String pictureName = "plane.png";
        pictureLabel = new ImageLabelHelper().getLabelWithImage(pictureName, PICTURE_WIDTH, PICTURE_HEIGHT);
    }

    private void setupTankBar(int tankCapacity) {
        fuelTankBar.setMaximum(tankCapacity);
        fuelTankBar.setValue(tankCapacity);
    }

    private void setupPanel() {
        panel.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        panel.setOpaque(false);
    }

    private void addComponents() {
        panel.add(nameLabel);
        panel.add(pictureLabel);
        panel.add(fuelTankBar);
    }

}
