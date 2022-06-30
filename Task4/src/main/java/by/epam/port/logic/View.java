package by.epam.port.logic;

import by.epam.port.bean.Ship;

public class View {
    private static View instance;

    private View() {

    }

    public static View getInstance() {
        if (instance == null) {
            instance = new View();
        }
        return instance;
    }

    public void print(String text) {
        System.out.println(text);
    }

    public void print(Ship ship) {
        System.out.println(ship);
    }
}
