package by.epam.port.bean;

/**
 * Порт имеет 5 {@link by.epam.port.bean.Pier причалов}, на каждом возможна разгрузка и загрузка.
 * Одновременно на одном причале находится только один корабль.
 */
public class Port {
    private final int CAPACITY = 120;
    private static Port instance;
    private int loaded;

    private Port() {

    }

    public static Port getInstance() {
        if (instance == null) {
            instance = new Port();
        }
        return instance;
    }

    public int getCapacity() {
        return CAPACITY;
    }

    public int getLoaded() {
        return loaded;
    }

    public void setLoaded(int loaded) {
        this.loaded = loaded;
    }
}
