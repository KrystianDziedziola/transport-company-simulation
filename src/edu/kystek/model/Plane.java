package edu.kystek.model;

import edu.kystek.controller.helper.AirportConstants;
import edu.kystek.controller.helper.EmptyTankException;
import edu.kystek.controller.helper.ImageHelper;

import javax.swing.*;

public class Plane {

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

    public void flip() {
        String pictureName = "planeToBase.png";
        pictureLabel.setIcon(new ImageHelper().getIcon(pictureName,
                AirportConstants.PLANE_WIDTH, AirportConstants.PLANE_HEIGHT));
    }

    private void setupLabels() {
        String pictureName = "planeFromBase.png";
        pictureLabel = new ImageHelper().getLabelWithImage(pictureName,
                AirportConstants.PLANE_WIDTH, AirportConstants.PLANE_HEIGHT);
    }

    private void setupTankBar(int tankCapacity) {
        fuelTankBar.setMaximum(tankCapacity);
        fuelTankBar.setValue(tankCapacity);
    }

    private void setupPanel() {
        panel.setSize(AirportConstants.PANEL_WIDTH, AirportConstants.PANEL_HEIGHT);
        panel.setOpaque(false);
    }

    private void addComponents() {
        panel.add(nameLabel);
        panel.add(pictureLabel);
        panel.add(fuelTankBar);
    }

    public void burnFuel(int fuel) throws EmptyTankException {
        if(isTankEmpty()) {
            throw new EmptyTankException();
        }
        fuelTankBar.setValue(fuelTankBar.getValue() - fuel);
    }

    public void explode() {
        pictureLabel.setIcon( new ImageHelper().getIcon("explode.png",
                AirportConstants.EXPLOSION_WIDTH, AirportConstants.EXPLOSION_HEIGHT));
        nameLabel.setText(nameLabel.getText() + " (exploded)");
    }

    private boolean isTankEmpty() {
        return fuelTankBar.getValue() <= 0;
    }



}
