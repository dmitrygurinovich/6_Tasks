package by.epam.port.main;

import by.epam.port.generator.ShipGenerator;

public class Main {
    public static void main(String[] args) {
        ShipGenerator shipGenerator = new ShipGenerator(20);
        Thread thread = new Thread(shipGenerator);

        thread.start();

    }
}
