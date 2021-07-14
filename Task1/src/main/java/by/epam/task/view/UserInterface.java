package by.epam.task.view;

import by.epam.task.entity.Library;
import by.epam.task.entity.UserRole;
import by.epam.task.logic.LibraryLogic;
import by.epam.task.logic.UserLogic;
import by.epam.task.logic.UsersBaseLogic;

import java.util.Scanner;

public class UserInterface {
    private final LibraryLogic libraryLogic;
    private static final Scanner in = new Scanner(System.in);
    private static final View view = new View();

    public UserInterface() {
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
                view.showBooks( this);
            case 2:
                libraryLogic.searchBooksByKeyword();
                adminMenu();
            case 3:
                libraryLogic.addBook();
                adminMenu();
            case 4:
                libraryLogic.editBook( this);
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
                view.showBooks( this);
                userMenu();
            case 2:
                libraryLogic.searchBooksByKeyword();
                userMenu();
            case 3:
                userLogic = new UserLogic();
                userLogic.suggestNewBook(Library.getInstance().getAuthorizedUser());
                userMenu();
            case 0:
                exit();
        }

    }

    public void authorization() {
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

        for (int i = 0; i < Library.getInstance().getUsers().size(); i++) {
            if (Library.getInstance().getUsers().get(i).getLogin().equals(login)) {
                if (Library.getInstance().getUsers().get(i).getPassword().equals(password)) {
                    authorized = true;
                    Library.getInstance().setAuthorizedUser(Library.getInstance().getUsers().get(i));
                    if (Library.getInstance().getAuthorizedUser().getRole().equals(UserRole.ADMINISTRATOR)) {
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
