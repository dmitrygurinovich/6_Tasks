package by.epam.library.presentation.impl;

import by.epam.library.presentation.DataFromConsole;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.presentation.View;

import java.util.Scanner;

public final class DataFromConsoleImpl implements DataFromConsole {
    private static final PresentationProvider viewProvider = PresentationProvider.getInstance();

    public DataFromConsoleImpl() {}

    @Override
    public int getMenuItem(int min, int max, String message) {
        Scanner in;
        View view;
        int number;

        in = new Scanner(System.in);
        view = viewProvider.getView();

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
        Scanner in;
        View view;
        int number;

        in = new Scanner(System.in);
        view = viewProvider.getView();

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
        Scanner in;
        View view;
        String text;

        in = new Scanner(System.in);
        view = viewProvider.getView();

        view.print(message);
        while (!in.hasNextLine()) {
            view.print(message);
            in.next();
        }
        text = in.nextLine();
        return text;
    }
}
