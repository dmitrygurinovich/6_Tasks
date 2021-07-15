package by.epam.library.view.impl;

import by.epam.library.bean.Library;
import by.epam.library.bean.UserRole;
import by.epam.library.service.LibraryService;
import by.epam.library.service.impl.LibraryServiceImpl;
import by.epam.library.service.impl.UserBaseServiceIml;
import by.epam.library.service.impl.UserServiceImpl;
import by.epam.library.view.UserInterface;
import by.epam.library.view.View;

import java.util.Scanner;

public class UserInterfaceImpl implements UserInterface {
    private static UserInterfaceImpl instance;
    private UserInterfaceImpl() {

    }

    public static UserInterfaceImpl getInstance() {
        if (instance == null) {
            instance = new UserInterfaceImpl();
        }
        return instance;
    }

    @Override
    public void adminMenu() {
        UserBaseServiceIml userBaseServiceIml;
        LibraryServiceImpl libraryService;
        View view;
        DataFromConsoleImpl dataFromConsole;

        int minMenuItem;
        int maxMenuItem;
        int menuItem;

        minMenuItem = 0;
        maxMenuItem = 5;
        libraryService = LibraryServiceImpl.getInstance();
        view = new View();
        userBaseServiceIml = UserBaseServiceIml.getInstance();
        dataFromConsole = DataFromConsoleImpl.getInstance();

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
                userBaseServiceIml.addUser();
                adminMenu();
            case 0:
                exit();
        }
    }

    @Override
    public void userMenu() {
        UserServiceImpl userService;
        int minMenuItem;
        int maxMenuItem;
        int menuItem;
        Library library;
        LibraryService libraryService;
        View view;
        DataFromConsoleImpl dataFromConsole;

        minMenuItem = 0;
        maxMenuItem = 4;
        library = Library.getInstance();
        libraryService = LibraryServiceImpl.getInstance();
        userService = UserServiceImpl.getInstance();
        view = new View();
        dataFromConsole = DataFromConsoleImpl.getInstance();

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

    @Override
    public void exit() {
        Scanner in;

        in = new Scanner(System.in);

        in.close();
        System.exit(0);
    }
}
