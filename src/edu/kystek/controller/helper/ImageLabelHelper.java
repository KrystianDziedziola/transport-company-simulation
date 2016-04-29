package edu.kystek.controller.helper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLabelHelper {

    public JLabel getLabelWithImage(String fileName, int labelWidth, int labelHeight) {
        try {
            JLabel pictureLabel;
            ClassLoader classLoader = getClass().getClassLoader();
            File pictureFile = new File(classLoader.getResource(fileName).getFile());
            BufferedImage image = ImageIO.read(pictureFile);
            Image scaledImage = image.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
            pictureLabel = new JLabel(new ImageIcon(scaledImage));
            pictureLabel.setSize(labelWidth, labelHeight);
            return pictureLabel;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
