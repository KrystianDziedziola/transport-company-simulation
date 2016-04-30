package edu.kystek;

import edu.kystek.controller.AirportController;
import edu.kystek.controller.FlightsGenerator;

public class Main {

    public static void main(String[] args) {
        new Main(args);

    }

    private Main(String[] args) {
        int numberOfPlanes = Integer.parseInt(args[0]);

        new FlightsGenerator(numberOfPlanes).start();

    }

}
