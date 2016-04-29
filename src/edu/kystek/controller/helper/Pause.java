package edu.kystek.controller.helper;

public class Pause {

    public static void pause(int timeInMilliseconds) {
        try {
            Thread.sleep(timeInMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
