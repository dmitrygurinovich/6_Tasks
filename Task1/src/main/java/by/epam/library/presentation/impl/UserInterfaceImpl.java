package by.epam.library.presentation.impl;

import by.epam.library.bean.Library;
import by.epam.library.bean.UserRole;
import by.epam.library.presentation.DataFromConsole;
import by.epam.library.presentation.UserInterface;
import by.epam.library.presentation.View;
import by.epam.library.presentation.ViewProvider;
import by.epam.library.service.LibraryService;
import by.epam.library.service.ServiceProvider;
import by.epam.library.service.UserBaseService;
import by.epam.library.service.UserService;

import java.util.Scanner;

public final class UserInterfaceImpl implements UserInterface {
    private final static ServiceProvider serviceProvider = ServiceProvider.getInstance();
    private static final ViewProvider viewProvider = ViewProvider.getInstance();

    public UserInterfaceImpl() {}

    @Override
    public void adminMenu() {
        View view = viewProvider.getView();
        DataFromConsole dataFromConsole = viewProvider.getDataFromConsole();
        LibraryService libraryService = serviceProvider.getLibraryService();
        UserBaseService userBaseService = serviceProvider.getUserBaseService();

        int minMenuItem;
        int maxMenuItem;
        int menuItem;

        minMenuItem = 0;
        maxMenuItem = 5;

        menuItem = dataFromConsole.getMenuItem(minMenuItem, maxMenuItem, "" +
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
                libraryService.searchBooksByKeyword();
                adminMenu();
            case 3:
                libraryService.addBook();
                adminMenu();
            case 4:
                libraryService.editBook();
                adminMenu();
            case 5:
                userBaseService.addUser();
                adminMenu();
            case 0:
                exit();
        }
    }

    @Override
    public void userMenu() {
        int minMenuItem;
        int maxMenuItem;
        int menuItem;

        Library library = Library.getInstance();
        View view = viewProvider.getView();
        DataFromConsole dataFromConsole = viewProvider.getDataFromConsole();
        LibraryService libraryService = serviceProvider.getLibraryService();
        UserService userService = serviceProvider.getUserService();

        minMenuItem = 0;
        maxMenuItem = 4;

        menuItem = dataFromConsole.getMenuItem(minMenuItem, maxMenuItem, "" +
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
                libraryService.searchBooksByKeyword();
                userMenu();
            case 3:
                userService.suggestNewBook(library.getAuthorizedUser());
                userMenu();
            case 0:
                exit();
        }
    }

    @Override
    public void authorization() {
        Library library= Library.getInstance();
        View view = viewProvider.getView();
        Scanner in = new Scanner(System.in);

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
            authorization();
        }
    }

    @Override
    public void exit() {
        Scanner in;

        in = new Scanner(System.in);

        in.close();
        System.exit(0);
    }
}
