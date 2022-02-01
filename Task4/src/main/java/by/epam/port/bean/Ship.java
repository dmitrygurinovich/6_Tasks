package by.epam.port.bean;

public class Ship {
    private int number;
    private Size size;
    private Action action;
    private int loaded;

    public Ship() {

    }

    public Ship(int number, Size size, Action action, int loaded) {
        this.number = number;
        this.size = size;
        this.action = action;
        this.loaded = loaded;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public int getLoaded() {
        return loaded;
    }

    public void setLoaded(int loaded) {
        this.loaded = loaded;
    }

    @Override
    public String toString() {
        return "Ship №" + number + " - [size: " + size + ", loaded: " + loaded + ", action: " + action + "]";
    }
}
