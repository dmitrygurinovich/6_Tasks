package by.epam.task.view;

import by.epam.task.entity.Library;
import by.epam.task.entity.UserRole;
import by.epam.task.logic.LibraryLogic;
import by.epam.task.logic.UserLogic;
import by.epam.task.logic.UsersBaseLogic;

import java.util.Scanner;

public class UserInterface {
    public UserInterface() {

    }

    public void adminMenu() {
        UsersBaseLogic usersBaseLogic;
        LibraryLogic libraryLogic;
        View view;

        int minMenuItem;
        int maxMenuItem;
        int menuItem;

        minMenuItem = 0;
        maxMenuItem = 5;
        libraryLogic = new LibraryLogic();
        view = new View();

        menuItem = getMenuItem(minMenuItem, maxMenuItem, "" +
                "+++ ADMIN MENU +++\n" +
                "1. Show catalog\n" +
                "2. Search book\n" +
                "3. Add book\n" +
                "4. Edit book\n" +
                "5. Add user\n" +
                "0. Exit");

        switch (menuItem) {
            case 1:
                view.showBooks();
            case 2:
                libraryLogic.searchBooksByKeyword();
                adminMenu();
            case 3:
                libraryLogic.addBook();
                adminMenu();
            case 4:
                libraryLogic.editBook();
                adminMenu();
            case 5:
                usersBaseLogic = new UsersBaseLogic();
                usersBaseLogic.addUser();
                adminMenu();
            case 0:
                exit();
        }

    }

    public void userMenu() {
        UserLogic userLogic;
        int minMenuItem;
        int maxMenuItem;
        int menuItem;
        Library library;
        LibraryLogic libraryLogic;
        View view;

        minMenuItem = 0;
        maxMenuItem = 4;
        library = Library.getInstance();
        libraryLogic = new LibraryLogic();
        userLogic = new UserLogic();
        view = new View();

        menuItem = getMenuItem(minMenuItem, maxMenuItem, "" +
                "+++ USER MENU +++\n" +
                "1. Show catalog\n" +
                "2. Search book\n" +
                "3. Suggest new book\n" +
                "0. Exit");

        switch (menuItem) {
            case 1:
                view.showBooks();
                userMenu();
            case 2:
                libraryLogic.searchBooksByKeyword();
                userMenu();
            case 3:
                userLogic.suggestNewBook(library.getAuthorizedUser());
                userMenu();
            case 0:
                exit();
        }

    }

    public void authorization() {
        String login;
        String password;
        boolean authorized;
        Library library;
        Scanner in;
        View view;

        authorized = false;
        library = Library.getInstance();
        in = new Scanner(System.in);
        view = new View();

        view.print("Enter login: ");
        while (!in.hasNextLine()) {
            in.next();
            view.print("Enter login: ");
        }
        login = in.nextLine();

        view.print("Enter password: ");
        while (!in.hasNextLine()) {
            in.next();
            view.print("Enter password: ");
        }
        password = in.nextLine();

        for (int i = 0; i < library.getUsers().size(); i++) {
            if (library.getUsers().get(i).getLogin().equals(login)) {
                if (library.getUsers().get(i).getPassword().equals(password)) {
                    authorized = true;
                    library.setAuthorizedUser(library.getUsers().get(i));
                    if (library.getAuthorizedUser().getRole().equals(UserRole.ADMINISTRATOR)) {
                        adminMenu();
                    } else {
                        userMenu();
                    }
                    break;
                }
            }
        }

        if (!authorized) {
            view.print("Invalid username or password! Try again!");
            authorization();
        }
    }

    public int getMenuItem(int min, int max, String message) {
        int number;
        Scanner in;
        View view;

        in = new Scanner(System.in);
        view = new View();

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

    public void exit() {
        Scanner in;

        in = new Scanner(System.in);

        in.close();
        System.exit(0);
    }

}
