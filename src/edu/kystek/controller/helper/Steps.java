package edu.kystek.controller.helper;

import java.awt.*;

public class Steps {

    private Point target, current;
    public static final int TIME_BETWEEN_STEPS = 50;

    public Steps(Point current, Point target) {
        this.current = current;
        this.target = target;
    }

    //FIXME: fix this two functions to get flight in straight line
    public int getX() {
//            return (int) ((target.getX() - current.getX()) / NUMBER_OF_STEPS);
//            return (int) ((target.getX() - current.getX()) / (current.getY() - target.getY()) * 5);
        return 5;
    }

    public int getY() {
//            return (int) ((current.getY() - target.getY()) / NUMBER_OF_STEPS);
        return 5;
    }

}
