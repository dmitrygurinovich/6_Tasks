package client.presentation.impl;

import client.presentation.UserInterface;
import client.service.ConsoleDataService;
import client.service.impl.ConsoleDataServiceImpl;

public class UserInterfaceImpl implements UserInterface {
    ConsoleDataService consoleDataService = ConsoleDataServiceImpl.getInstance();

    public String adminMenu() {
        int menuItem;

        menuItem = consoleDataService.getNumFromConsole("" +
                        "|+++ ADMIN MENU +++|\n" +
                        "1. Show all files\n" +
                        "2. Search by keyword\n",
                       1, 2);

        switch (menuItem) {
            case 1:
                return "service&get_all_files";
            case 2:
                return "service&search_files&Petrov";
            default:
                return "Invalid command!";
        }
    }

    @Override
    public void userMenu() {

    }
}
