package by.epam.task.view;

import by.epam.task.entity.Library;
import by.epam.task.entity.UserRole;
import by.epam.task.logic.LibraryLogic;
import by.epam.task.logic.UserLogic;
import by.epam.task.logic.UsersBaseLogic;

import java.util.Scanner;

public class UserInterface {
    private final Library library;
    private final LibraryLogic libraryLogic;
    private static final Scanner in = new Scanner(System.in);
    private static final View view = new View();

    public UserInterface(Library library) {
        this.library = library;
        this.libraryLogic = new LibraryLogic();
    }

    public void adminMenu() {
        UsersBaseLogic usersBaseLogic;

        int minMenuItem;
        int maxMenuItem;
        int menuItem;

        minMenuItem = 0;
        maxMenuItem = 5;

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
                view.showBooks(library, this);
            case 2:
                libraryLogic.searchBooksByKeyword(library);
                adminMenu();
            case 3:
                libraryLogic.addBook(library);
                adminMenu();
            case 4:
                libraryLogic.editBook(library, this);
                adminMenu();
            case 5:
                usersBaseLogic = new UsersBaseLogic();
                usersBaseLogic.addUser(library);
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

        minMenuItem = 0;
        maxMenuItem = 4;

        menuItem = getMenuItem(minMenuItem, maxMenuItem, "" +
				"+++ USER MENU +++\n" +
				"1. Show catalog\n" +
				"2. Search book\n" +
				"3. Suggest new book\n" +
				"0. Exit");

        switch (menuItem) {
            case 1:
                view.showBooks(library, this);
                userMenu();
            case 2:
                libraryLogic.searchBooksByKeyword(library);
                userMenu();
            case 3:
                userLogic = new UserLogic();
                userLogic.suggestNewBook(library.getAuthorizedUser());
                userMenu();
            case 0:
                exit();
        }

    }

    public void authorisation() {
        String login;
        String password;
        boolean authorized;

        authorized = false;

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
            authorisation();
        }
    }

    public int getMenuItem(int min, int max, String message) {
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

    public void exit() {
        in.close();
        System.exit(0);
    }

}
