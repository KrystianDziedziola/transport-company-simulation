package edu.kystek.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Plane {

    private static final int WIDTH = 100, HEIGHT = 100;

    private String name;
    private JLabel pictureLabel;

    public Plane(String name) {
        this.name = name;
        createPictureLabel();
    }

    private void createPictureLabel() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File pictureFile = new File(classLoader.getResource("plane.png").getFile());
            BufferedImage image = ImageIO.read(pictureFile);
            Image scaledImage = image.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
            pictureLabel = new JLabel(new ImageIcon(scaledImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public JLabel getPictureLabel() {
        return pictureLabel;
    }
}
