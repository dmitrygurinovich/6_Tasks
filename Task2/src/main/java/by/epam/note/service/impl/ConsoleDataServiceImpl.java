package by.epam.note.service.impl;

import by.epam.note.presentation.PresentationProvider;
import by.epam.note.presentation.View;
import by.epam.note.service.ConsoleDataService;

import java.util.Scanner;

public class ConsoleDataServiceImpl implements ConsoleDataService {
    public ConsoleDataServiceImpl() {}

    @Override
    public int getNumFromConsole(String message, int min, int max) {
        int number;
        View view;
        Scanner in;

        view = PresentationProvider.getInstance().getView();
        in = new Scanner(System.in);

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
        View view;
        Scanner in;

        view = PresentationProvider.getInstance().getView();
        in = new Scanner(System.in);

        view.print(message);

        while (!in.hasNextLine()) {
            view.print(message);
            in.next();
        }
        text = in.nextLine();
        return text;
    }
}
