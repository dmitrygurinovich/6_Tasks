package by.epam.note.service.impl;

import by.epam.note.presentation.PresentationProvider;
import by.epam.note.presentation.View;
import by.epam.note.service.ConsoleDataService;
import java.util.Scanner;

public class ConsoleDataServiceImpl implements ConsoleDataService {

    @Override
    public int getNumFromConsole(String message, int min, int max) {
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        View view = presentationProvider.getView();
        Scanner in = new Scanner(System.in);

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
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        View view = presentationProvider.getView();
        Scanner in = new Scanner(System.in);

        view.print(message);

        while (!in.hasNextLine()) {
            view.print(message);
            in.next();
        }
        return in.nextLine();
    }
}
