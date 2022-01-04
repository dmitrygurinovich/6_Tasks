package by.epam.library.presentation.impl;

import by.epam.library.bean.Library;
import by.epam.library.bean.UserRole;
import by.epam.library.controller.Controller;
import by.epam.library.controller.impl.MainController;
import by.epam.library.presentation.DataFromConsole;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.presentation.UserInterface;
import by.epam.library.presentation.View;

import java.util.Scanner;

public final class UserInterfaceImpl implements UserInterface {
    private static final PresentationProvider presentationProvider = PresentationProvider.getInstance();
    private static final DataFromConsole dataFromConsole = presentationProvider.getDataFromConsole();
    private static final Controller controller = new MainController();

    public UserInterfaceImpl() {}

    @Override
    public void adminMenu() {
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
                controller.doAction("presentation show_books");
            case 2:
                controller.doAction("service search_books");
                controller.doAction("presentation admin_menu");
            case 3:
                controller.doAction("service add_book");
                controller.doAction("presentation admin_menu");
            case 4:
                controller.doAction("service edit_book");
                controller.doAction("presentation admin_menu");
            case 5:
                controller.doAction("service add_user");
                controller.doAction("presentation admin_menu");
            case 0:
                controller.doAction("presentation exit");
        }
    }

    @Override
    public void userMenu() {
        int minMenuItem;
        int maxMenuItem;
        int menuItem;

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
                controller.doAction("presentation show_books");
                controller.doAction("presentation user_menu");
            case 2:
                controller.doAction("service search_books");
                controller.doAction("presentation user_menu");
            case 3:
                controller.doAction("service suggest_book");
                controller.doAction("presentation user_menu");
            case 0:
                controller.doAction("presentation exit");
        }
    }

    @Override
    public void authorization() {
        Library library;
        View view;
        Scanner in;
        String login;
        String password;
        boolean authorized;

        authorized = false;
        library= Library.getInstance();
        view = presentationProvider.getView();
        in = new Scanner(System.in);

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
