package by.epam.library.presentation.impl;

import by.epam.library.presentation.DataFromConsole;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.presentation.View;

import java.util.Scanner;

public final class DataFromConsoleImpl implements DataFromConsole {

    @Override
    public int getNumFromConsole(String message, int min, int max) {
        Scanner in = new Scanner(System.in);
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        View view = presentationProvider.getView();

        view.print(message);
        while (!in.hasNextInt()) {
            view.print(message);
            in.next();
        }
        int number = in.nextInt();
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
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        View view = presentationProvider.getView();

        view.print(message);
        while (!in.hasNextLine()) {
            view.print(message);
            in.next();
        }

        return in.nextLine();
    }
}
