package by.epam.library.presentation.impl;

import by.epam.library.bean.Library;
import by.epam.library.bean.UserRole;
import by.epam.library.controller.Controller;
import by.epam.library.controller.ControllerProvider;
import by.epam.library.presentation.DataFromConsole;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.presentation.UserInterface;
import by.epam.library.presentation.View;

import java.util.Scanner;

public final class UserInterfaceImpl implements UserInterface {

    @Override
    public void adminMenu() {
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        DataFromConsole dataFromConsole = presentationProvider.getDataFromConsole();
        ControllerProvider controllerProvider = ControllerProvider.getInstance();
        Controller mainController = controllerProvider.getMainController();
        int minMenuItem = 0;
        int maxMenuItem = 5;
        int menuItem = dataFromConsole.getNumFromConsole("" +
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
                mainController.doAction("presentation show_books");
            case 2:
                mainController.doAction("service search_books");
                mainController.doAction("presentation admin_menu");
            case 3:
                mainController.doAction("service add_book");
                mainController.doAction("presentation admin_menu");
            case 4:
                mainController.doAction("service edit_book");
                mainController.doAction("presentation admin_menu");
            case 5:
                mainController.doAction("service add_user");
                mainController.doAction("presentation admin_menu");
            case 0:
                mainController.doAction("presentation exit");
        }
    }

    @Override
    public void userMenu() {
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        DataFromConsole dataFromConsole = presentationProvider.getDataFromConsole();
        ControllerProvider controllerProvider = ControllerProvider.getInstance();
        Controller mainController = controllerProvider.getMainController();
        int minMenuItem = 0;
        int maxMenuItem = 4;
        int menuItem = dataFromConsole.getNumFromConsole( "" +
                "+++ USER MENU +++\n" +
                "1. Show catalog\n" +
                "2. Search book\n" +
                "3. Suggest new book\n" +
                "0. Exit",
                minMenuItem, maxMenuItem);

        switch (menuItem) {
            case 1:
                mainController.doAction("presentation show_books");
                mainController.doAction("presentation user_menu");
            case 2:
                mainController.doAction("service search_books");
                mainController.doAction("presentation user_menu");
            case 3:
                mainController.doAction("service suggest_book");
                mainController.doAction("presentation user_menu");
            case 0:
                mainController.doAction("presentation exit");
        }
    }

    @Override
    public void authorization() {
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        DataFromConsole dataFromConsole = presentationProvider.getDataFromConsole();
        View view = presentationProvider.getView();
        Library library= Library.getInstance();
        boolean authorized = false;

        String login = dataFromConsole.getStringFromConsole("Enter login: ");
        String password = dataFromConsole.getStringFromConsole("Enter password: ");

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