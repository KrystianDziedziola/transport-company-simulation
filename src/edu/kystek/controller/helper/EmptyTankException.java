package edu.kystek.controller.helper;

public class EmptyTankException extends Exception {

    public EmptyTankException() {
        super("Tank is empty.");
    }
}
