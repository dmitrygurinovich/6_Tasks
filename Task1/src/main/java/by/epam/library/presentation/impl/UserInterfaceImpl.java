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
    private static final PresentationProvider PRESENTATION_PROVIDER = PresentationProvider.getInstance();
    private static final DataFromConsole DATA_FROM_CONSOLE = PRESENTATION_PROVIDER.getDataFromConsole();
    private static final Controller MAIN_CONTROLLER = new MainController();

    public UserInterfaceImpl() {

    }

    @Override
    public void adminMenu() {
        int minMenuItem = 0;
        int maxMenuItem = 5;
        int menuItem = DATA_FROM_CONSOLE.getNumFromConsole("" +
                "+++ ADMIN MENU +++\n" +
                "1. Show catalog\n" +
                "2. Search book\n" +
                "3. Add book\n" +
                "4. Edit book\n" +
                "5. Add user\n" +
                "0. Exit",
                minMenuItem, maxMenuItem);

        switch (menuItem) {
            case 1:
                MAIN_CONTROLLER.doAction("presentation show_books");
            case 2:
                MAIN_CONTROLLER.doAction("service search_books");
                MAIN_CONTROLLER.doAction("presentation admin_menu");
            case 3:
                MAIN_CONTROLLER.doAction("service add_book");
                MAIN_CONTROLLER.doAction("presentation admin_menu");
            case 4:
                MAIN_CONTROLLER.doAction("service edit_book");
                MAIN_CONTROLLER.doAction("presentation admin_menu");
            case 5:
                MAIN_CONTROLLER.doAction("service add_user");
                MAIN_CONTROLLER.doAction("presentation admin_menu");
            case 0:
                MAIN_CONTROLLER.doAction("presentation exit");
        }
    }

    @Override
    public void userMenu() {
        int minMenuItem = 0;
        int maxMenuItem = 4;
        int menuItem = DATA_FROM_CONSOLE.getNumFromConsole( "" +
                "+++ USER MENU +++\n" +
                "1. Show catalog\n" +
                "2. Search book\n" +
                "3. Suggest new book\n" +
                "0. Exit",
                minMenuItem, maxMenuItem);

        switch (menuItem) {
            case 1:
                MAIN_CONTROLLER.doAction("presentation show_books");
                MAIN_CONTROLLER.doAction("presentation user_menu");
            case 2:
                MAIN_CONTROLLER.doAction("service search_books");
                MAIN_CONTROLLER.doAction("presentation user_menu");
            case 3:
                MAIN_CONTROLLER.doAction("service suggest_book");
                MAIN_CONTROLLER.doAction("presentation user_menu");
            case 0:
                MAIN_CONTROLLER.doAction("presentation exit");
        }
    }

    @Override
    public void authorization() {
        Library library= Library.getInstance();
        View view = PRESENTATION_PROVIDER.getView();
        boolean authorized = false;

        String login = DATA_FROM_CONSOLE.getStringFromConsole("Enter login: ");
        String password = DATA_FROM_CONSOLE.getStringFromConsole("Enter password: ");

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
        Scanner in = new Scanner(System.in);
        in.close();
        System.exit(0);
    }
}
