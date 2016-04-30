package edu.kystek;

import edu.kystek.controller.FlightsGenerator;

public class Main {

    private static final int NUMBER_OF_PLANES_ARGUMENT_INDEX = 0;
    private static final int AVERAGE_TANK_CAPACITY_ARGUMENT_INDEX = 1;

    public static void main(String[] args) {
        new Main(args);
    }

    private Main(String[] args) {
        int numberOfPlanes = Integer.parseInt(args[NUMBER_OF_PLANES_ARGUMENT_INDEX]);
        int averageFuelTankCapacity = Integer.parseInt(args[AVERAGE_TANK_CAPACITY_ARGUMENT_INDEX]);
        new FlightsGenerator(numberOfPlanes, averageFuelTankCapacity).start();
    }

}
