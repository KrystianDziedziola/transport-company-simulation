package edu.kystek.controller.helper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageLabelHelper {

    public JLabel getLabelWithImage(String fileName, int labelWidth, int labelHeight) {
        JLabel pictureLabel = new JLabel();
        pictureLabel.setIcon(getIcon(fileName, labelWidth, labelHeight));
        pictureLabel.setSize(labelWidth, labelHeight);
        return pictureLabel;
    }

    public Icon getIcon(String fileName, int width, int height) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File pictureFile = new File(classLoader.getResource(fileName).getFile());
            BufferedImage image = ImageIO.read(pictureFile);
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
