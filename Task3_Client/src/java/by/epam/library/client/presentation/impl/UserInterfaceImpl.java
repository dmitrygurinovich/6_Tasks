package by.epam.library.client.presentation.impl;

import by.epam.library.client.presentation.UserInterface;
import by.epam.library.client.service.ConsoleDataService;
import by.epam.library.client.service.impl.ConsoleDataServiceImpl;

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
    public String userMenu() {
        int menuItem;

        menuItem = consoleDataService.getNumFromConsole("" +
                        "|+++ USER MENU +++|\n" +
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
}
