package by.epam.library.client.presentation.impl;

import by.epam.library.client.controller.ClientController;
import by.epam.library.client.controller.impl.MainController;
import by.epam.library.client.presentation.UserInterface;
import by.epam.library.client.service.ConsoleDataService;
import by.epam.library.client.service.impl.ConsoleDataServiceImpl;

public class UserInterfaceImpl implements UserInterface {
    ConsoleDataService consoleDataService = ConsoleDataServiceImpl.getInstance();
    ClientController controller = new MainController();


    public void adminMenu() {
        int menuItem;

        menuItem = consoleDataService.getNumFromConsole("" +
                        "|+++ ADMIN MENU +++|\n" +
                        "1. Show all files\n" +
                        "2. Search by keyword\n",
                       1, 2);

        switch (menuItem) {
            case 1:
                controller.action("service show_all_files");
            case 2:
                controller.action("service search Petrov");
        }
    }

    @Override
    public void userMenu() {

    }
}
