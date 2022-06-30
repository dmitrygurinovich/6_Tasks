package by.epam.port.logic;

import by.epam.port.bean.Port;
import by.epam.port.bean.Ship;

public class PortLogic {
    private static PortLogic instance;
    private final Port port = Port.getInstance();
    private final View view = View.getInstance();

    private PortLogic() {

    }

    public static PortLogic getInstance() {
        if (instance == null) {
            instance = new PortLogic();
        }
        return instance;
    }

    public void shipDispatcher( Ship ship) {

    }

    public void unloadShip(Ship ship) {
        int portLoaded = port.getLoaded();
        int portCapacity = port.getCapacity();
        int freePortSpace = portCapacity - portLoaded;

        if (freePortSpace >= ship.getLoaded()) {
            portLoaded += ship.getLoaded();
            ship.setLoaded(0);
            view.print("Ship №"+ ship.getNumber() + " unloaded!");
        } else {
            view.print("Ship №" + ship.getNumber() + " staying in the water area!");
        }
    }

    public void loadShip(Ship ship) {
        int portLoaded = port.getLoaded();
        int shipCapacity = ship.getLoaded();
        int freeShipSpace = shipCapacity - ship.getLoaded();

        if (portLoaded >= ship.getLoaded()) {
            port.setLoaded(portLoaded - freeShipSpace);
            ship.setLoaded(ship.getLoaded() + freeShipSpace);
            view.print("Ship №" + ship.getNumber() + " loaded!");
        } else {
            view.print("Ship №" + ship.getNumber() + " staying in the water area!");
        }
    }
}
