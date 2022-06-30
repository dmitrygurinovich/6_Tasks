package by.epam.port.generator;

import by.epam.port.bean.Action;
import by.epam.port.bean.Ship;
import by.epam.port.bean.Size;
import by.epam.port.bean.WaterArea;
import by.epam.port.logic.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Генератор рамнодмных {@link by.epam.port.bean.Ship кораблей}, сразу помещае их в
 * {@link by.epam.port.bean.WaterArea Water Area}.
 */
public class ShipGenerator implements Runnable {
    private final WaterArea waterArea = WaterArea.getInstance();
    private final ArrayList<Ship> shipsQueue = waterArea.getQueueToThePort();
    private final View view = View.getInstance();
    private final int shipCount;

    public ShipGenerator(int shipCount) {
        this.shipCount = shipCount;
    }

    @Override
    public void run() {
        view.print("Queue to the port:");
        int startShipCount = 0;

        synchronized (waterArea) {
            while (startShipCount < shipCount){
                Ship ship = getRandomShip(startShipCount + 1);
                view.print(ship);
                shipsQueue.add(ship);
                startShipCount++;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        view.print("...end.");
    }

    private Ship getRandomShip(int number) {
        Ship ship = new Ship();

        ship.setNumber(number);
        ship.setSize(getRandomSize());

        int loaded = getRandomNumFromList();
        ship.setLoaded(loaded);

        if (ship.getLoaded() == 0) {
            ship.setAction(Action.LOADING);
        } else if (ship.getLoaded() == ship.getSize().getValue()) {
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
        int[] numbers = new int[]{0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50};
        Random random = new Random();
        return numbers[random.nextInt(numbers.length)];
    }
}
