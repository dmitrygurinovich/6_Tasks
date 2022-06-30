package by.epam.port.bean;

import java.util.ArrayList;

/**
 * Очередь из кораблей в порт, размер не ограничен.
 * Отсюда {@link by.epam.port.bean.Ship корабли} попадают в {@link by.epam.port.bean.Port порт}
 */
public class WaterArea {
    private static WaterArea instance;
    private final ArrayList<Ship> queueToThePort;

    private WaterArea() {
        queueToThePort = new ArrayList<>();
    }

    public static WaterArea getInstance() {
        if (instance == null) {
            instance = new WaterArea();
        }
        return instance;
    }

    public ArrayList<Ship> getQueueToThePort() {
        return queueToThePort;
    }
}
