package by.epam.library.client.presentation.impl;

import by.epam.library.client.presentation.UserInterface;
import by.epam.library.client.service.ClientService;
import by.epam.library.client.service.ConsoleDataService;
import by.epam.library.client.service.ServiceProvider;

public class UserInterfaceImpl implements UserInterface {
    private static final ConsoleDataService CONSOLE_DATA = ServiceProvider.getInstance().getConsoleDataService();
    private final ClientService CLIENT_SERVICE = ServiceProvider.getInstance().getClientService();

    @Override
    public String adminMenu() {
        int menuItem;

        menuItem = CONSOLE_DATA.getNumFromConsole("" +
                        "|+++ ADMIN MENU +++|\n" +
                        "1. Show all files\n" +
                        "2. Search by keyword\n" +
                        "3. Edit file\n" +
                        "4. Add file\n" +
                        "5. Delete file\n",
                1, 5);

        switch (menuItem) {
            case 1:
                return "service&show_all_files";
            case 2:
                return CLIENT_SERVICE.searchFiles();
            case 3:
                return CLIENT_SERVICE.editFile();
            case 4:
                return CLIENT_SERVICE.addFile();
            case 5:
                return CLIENT_SERVICE.deleteFile();
            default:
                return "Invalid command!";
        }
    }

    @Override
    public String userMenu() {
        int menuItem;

        menuItem = CONSOLE_DATA.getNumFromConsole("" +
                        "|+++ USER MENU +++|\n" +
                        "1. Show all files\n" +
                        "2. Search by keyword\n",
                1, 2);

        switch (menuItem) {
            case 1:
                return "service&show_all_files";
            case 2:
                return CLIENT_SERVICE.searchFiles();
            default:
                return "Invalid command!";
        }
    }

}
