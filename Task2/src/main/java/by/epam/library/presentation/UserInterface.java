package by.epam.library.presentation;

import by.epam.library.logic.NotesBaseLogic;

import java.util.Scanner;

public class UserInterface {
    public UserInterface() {

    }

    public void menu() {
        int menuItem;
        View view;
        NotesBaseLogic logic;
        Scanner in;

        view = new View();
        logic = new NotesBaseLogic();
        in = new Scanner(System.in);

        view.print("" +
                "#### MENU ####\n" +
                "1. Show all notes\n" +
                "2. Search\n" +
                "3. Add note\n" +
                "4. Edit note\n" +
                "5. Delete note\n" +
                "0. Exit\n");

        menuItem = getNumFromConsole("Enter number 0 - 5:", 0, 5);

        switch (menuItem) {
            case 0:
                in.close();
                System.exit(0);
            case 1:
                logic.showAllNotes();
                menu();
            case 2:
                logic.searchNotes();
                menu();
            case 3:
                logic.addNote();
                menu();
            case 4:
                logic.editNote();
                menu();
            case 5:
                logic.deleteNote();
                menu();
        }
    }

    public int getNumFromConsole(String message, int min, int max) {
        int number;
        View view;
        Scanner in;

        view = new View();
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
}
