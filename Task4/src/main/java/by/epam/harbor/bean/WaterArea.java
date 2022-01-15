package by.epam.harbor.bean;

import java.util.LinkedList;
import java.util.Queue;

public class WaterArea {
    private static WaterArea instance;
    private Queue<Ship> queueForLoading = new LinkedList<>();
    private Queue<Ship> queueForUnloading = new LinkedList<>();

    private WaterArea() {

    }

    public static WaterArea getInstance() {
        if (instance == null) {
            instance = new WaterArea();
        }
        return instance;
    }

    public static void setInstance(WaterArea instance) {
        WaterArea.instance = instance;
    }

    public Queue<Ship> getQueueForLoading() {
        return queueForLoading;
    }

    public void setQueueForLoading(Queue<Ship> queueForLoading) {
        this.queueForLoading = queueForLoading;
    }

    public Queue<Ship> getQueueForUnloading() {
        return queueForUnloading;
    }

    public void setQueueForUnloading(Queue<Ship> queueForUnloading) {
        this.queueForUnloading = queueForUnloading;
    }
}
