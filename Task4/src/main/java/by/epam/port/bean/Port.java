package by.epam.port.bean;

/**
 * Порт имеет 5 {@link by.epam.port.bean.Pier причалов}, на каждом возможна разгрузка и загрузка.
 * Одновременно на одном причале находится только один корабль.
 */
public class Port {
    private final int CAPACITY = 120;
    private int loaded;

    public Port() {

    }

    public int getCapacity() {
        return CAPACITY;
    }
}
