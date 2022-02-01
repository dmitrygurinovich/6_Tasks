package by.epam.port.generator;

import by.epam.port.bean.Action;
import by.epam.port.bean.Ship;
import by.epam.port.bean.Size;
import by.epam.port.bean.WaterArea;

import java.util.Random;

public class ShipGenerator implements Runnable {
    private final WaterArea waterArea = WaterArea.getInstance();
    private final int shipCount;

    public ShipGenerator(int shipCount) {
        this.shipCount = shipCount;
    }

    @Override
    public void run() {
        int startShipCount = 0;

        synchronized (waterArea) {
            while (startShipCount < shipCount){
                Ship ship = getRandomShip(startShipCount + 1);
                System.out.println(ship);
                waterArea.getQueueToThePort().add(ship);
                startShipCount++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Ship getRandomShip(int number) {
        Ship ship = new Ship();

        ship.setNumber(number);
        ship.setSize(getRandomSize());

        int loaded = getRandomNumFromList();
        while (loaded > ship.getSize().getValue()) {
            loaded = getRandomNumFromList();
        }
        ship.setLoaded(loaded);

        if (ship.getLoaded() == ship.getSize().getValue()) {
            ship.setAction(Action.UNLOADING);
        } else {
            ship.setAction(getRandomAction());
        }

        return ship;
    }

    private Action getRandomAction() {
        Random random = new Random();
        return Action.values()[random.nextInt(Action.values().length)];
    }

    private Size getRandomSize() {
        Random random = new Random();
        return Size.values()[random.nextInt(Size.values().length)];
    }

    private int getRandomNumFromList() {
        int[] numbers = new int[]{5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
        Random random = new Random();
        return numbers[random.nextInt(numbers.length)];
    }
}
