package by.epam.port.bean;

import java.util.LinkedList;
import java.util.Queue;

public class WaterArea {
    private static WaterArea instance;
    private final Queue<Ship> queueToThePort = new LinkedList<>();

    private WaterArea() {

    }

    public static WaterArea getInstance() {
        if (instance == null) {
            instance = new WaterArea();
        }
        return instance;
    }

    public Queue<Ship> getQueueToThePort() {
        return queueToThePort;
    }
}
