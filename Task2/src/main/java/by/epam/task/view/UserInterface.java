package by.epam.task.view;

import java.util.Scanner;

public class UserInterface {
    private static final Scanner in = new Scanner(System.in);
    private final View view;

    public UserInterface() {
        view = new View();
    }

    public void menu() {
        int menuItem;

        view.print("" +
                "#### MENU ####\n" +
                "1. Show all notes\n" +
                "2. Add note\n" +
                "3. Edit note\n" +
                "4. Delete note\n" +
                "0. Exit\n");

        menuItem = getNumFromConsole("Enter number 0 - 4:", 0, 4);

        switch (menuItem) {
            case 0:
                in.close();
                System.exit(0);
            case 1:
                // show notes
            case 2:
                // add note
            case 3:
                // edit note
            case 4:
                // delete note
        }
    }

    public int getNumFromConsole(String message, int min, int max) {
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

    public String getStringFromConsole(String message) {
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
