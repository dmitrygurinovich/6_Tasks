package by.epam.harbor.bean;

import java.util.Objects;

public class Ship {
    private int number;
    private int capacity;
    private int loaded;

    public Ship() {

    }

    public Ship(int number, int capacity, int loaded) {
        this.number = number;
        this.capacity = capacity;
        this.loaded = loaded;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if (capacity < 0) {
            this.capacity = 0;
        } else {
            this.capacity = capacity;
        }
    }

    public int getLoaded() {
        return loaded;
    }

    public void setLoaded(int loaded) {
        if (loaded < 0) {
            this.loaded = 0;
        } else {
            this.loaded = loaded;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return number == ship.number && capacity == ship.capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, capacity);
    }

    @Override
    public String toString() {
        return "Ship №" + number + ", loaded: " + loaded + "/" + capacity;
    }
}
