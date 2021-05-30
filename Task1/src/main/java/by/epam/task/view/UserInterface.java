package by.epam.task.view;

import by.epam.task.entity.Library;
import by.epam.task.entity.UserRole;
import by.epam.task.logic.LibraryLogic;
import by.epam.task.logic.UserLogic;
import by.epam.task.logic.UsersBaseLogic;

import java.util.Scanner;

public class UserInterface {
    private final Library library;

    public UserInterface() {
        this.library = new Library();
    }

    public void adminMenu() {
        LibraryLogic libraryLogic;
        UsersBaseLogic usersBaseLogic;
        View view;

        int minMenuItem;
        int maxMenuItem;
        int menuItem;

        view = new View();
        minMenuItem = 0;
        maxMenuItem = 6;

        menuItem = getMenuItem(minMenuItem, maxMenuItem, "" +
				"+++ ADMIN MENU +++\n" +
				"1. Show catalog\n" +
				"2. Search book\n" +
				"3. Add book\n" +
				"4. Edit book\n" +
				"5. Add user\n" +
				"6. Add book's description\n" +
				"0. Exit");
        if (menuItem == 1) {
            view.showBooks(library);
        } else if (menuItem == 2) {
            // TODO реализовать поиск
        } else if (menuItem == 3) {
            libraryLogic = new LibraryLogic();
            libraryLogic.addBook(library);
            adminMenu();
        } else if (menuItem == 4) {
            // TODO реализовать правку книги
        } else if (menuItem == 5) {
            usersBaseLogic = new UsersBaseLogic();
            usersBaseLogic.addUser(library);
            adminMenu();
        } else if (menuItem == 0) {
            exit();
        }
    }

    public void userMenu() {
        UserLogic userLogic;
        View view;
        int minMenuItem;
        int maxMenuItem;
        int item;

        minMenuItem = 0;
        maxMenuItem = 4;


        item = getMenuItem(minMenuItem, maxMenuItem, "" +
				"+++ USER MENU +++\n" +
				"1. Show catalog\n" +
				"2. Search book\n" +
				"3. Suggest new book\n" +
				"0. Exit");

        if (item == 1) {
			view = new View();
            view.showBooks(library);
            userMenu();
        }
        if (item == 2) {
            // TODO реализовать поиск
        }
        if (item == 3) {
            userLogic = new UserLogic();
            userLogic.suggestNewBook(library.getAuthorizedUser());
            userMenu();
        }
        if (item == 0) {
            exit();
        }

    }

    public void authorisation() {
        View view;
        String login;
        String password;
        boolean authorized;
        Scanner in;

        view = new View();
        authorized = false;

        view.print("Enter login: ");
        in = new Scanner(System.in);
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
		View view;
        int number;
        Scanner in;

        view = new View();

        view.print(message);

        in = new Scanner(System.in);
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
