package by.epam.library.view.impl;

import by.epam.library.view.DataFromConsole;
import by.epam.library.view.View;
import by.epam.library.view.ViewProvider;

import java.util.Scanner;

public final class DataFromConsoleImpl implements DataFromConsole {
    private static final ViewProvider viewProvider = ViewProvider.getInstance();

    public DataFromConsoleImpl() {}

    @Override
    public int getMenuItem(int min, int max, String message) {

        Scanner in = new Scanner(System.in);
        View view = viewProvider.getView();

        int number;

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

        Scanner in = new Scanner(System.in);
        View view = viewProvider.getView();
        int number;

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
        Scanner in = new Scanner(System.in);
        View view = viewProvider.getView();

        String text;

        view.print(message);

        while (!in.hasNextLine()) {
            view.print(message);
            in.next();
        }
        text = in.nextLine();
        return text;
    }
}
