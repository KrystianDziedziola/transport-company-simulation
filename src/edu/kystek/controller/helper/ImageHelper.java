package edu.kystek.controller.helper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHelper {

    public JLabel getLabelWithImage(String fileName, int labelWidth, int labelHeight) {
        JLabel pictureLabel = new JLabel();
        pictureLabel.setIcon(getIcon(fileName, labelWidth, labelHeight));
        pictureLabel.setSize(labelWidth, labelHeight);
        return pictureLabel;
    }

    public Icon getIcon(String fileName, int width, int height) {
        return new ImageIcon(getImage(fileName, width, height));
    }

    private Image getImage(String fileName, int width, int height) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File pictureFile = new File(classLoader.getResource(fileName).getFile());
            BufferedImage image  = ImageIO.read(pictureFile);
            return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
