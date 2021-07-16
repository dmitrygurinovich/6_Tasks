package by.epam.library.view.impl;

import by.epam.library.view.DataFromConsole;

import java.util.Scanner;

public final class DataFromConsoleImpl implements DataFromConsole {
    private static DataFromConsoleImpl instance;

    private DataFromConsoleImpl() {

    }

    public static DataFromConsoleImpl getInstance(){
        if (instance == null) {
            instance = new DataFromConsoleImpl();
        }
        return instance;
    }

    @Override
    public int getMenuItem(int min, int max, String message) {
        int number;
        Scanner in;
        ViewImpl view;

        in = new Scanner(System.in);
        view = ViewImpl.getInstance();

        view.print(message);

        while (!in.hasNextInt()) {
            view.print(message);
            in.next();
        }
        number = in.nextInt();
        in.nextLine();
        if (number >= min && number <= max) {
            return number;
        } else {
            return getMenuItem(min, max, message);
        }
    }

    @Override
    public int getNumFromConsole(String message, int min, int max) {
        int number;
        Scanner in;
        ViewImpl view;

        in = new Scanner(System.in);
        view = ViewImpl.getInstance();

        view.print(message);
        while (!in.hasNextInt()) {
            view.print(message);
            in.next();
        }
        number = in.nextInt();
        in.nextLine();

        if (number >= min && number <= max) {
            return number;
        } else {
            return getNumFromConsole(message, min, max);
        }
    }

    @Override
    public String getStringFromConsole(String message) {
        String text;
        Scanner in;
        ViewImpl view;

        in = new Scanner(System.in);
        view = ViewImpl.getInstance();

        view.print(message);

        while (!in.hasNextLine()) {
            view.print(message);
            in.next();
        }
        text = in.nextLine();
        return text;
    }
}
