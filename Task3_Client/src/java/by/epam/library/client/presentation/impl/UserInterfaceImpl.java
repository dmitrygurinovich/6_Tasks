package by.epam.library.client.presentation.impl;

import by.epam.library.client.bean.ClientUserSession;
import by.epam.library.client.bean.File;
import by.epam.library.client.presentation.UserInterface;
import by.epam.library.client.service.ClientService;
import by.epam.library.client.service.ConsoleDataService;
import by.epam.library.client.service.ServiceProvider;

public class UserInterfaceImpl implements UserInterface {
    ConsoleDataService consoleDataService = ServiceProvider.getInstance().getConsoleDataService();

    public String adminMenu() {
        int menuItem;

        menuItem = consoleDataService.getNumFromConsole("" +
                        "|+++ ADMIN MENU +++|\n" +
                        "1. Show all files\n" +
                        "2. Search by keyword\n" +
                        "3. Edit file\n",
                       1, 3);

        switch (menuItem) {
            case 1:
                return "service&show_all_files";
            case 2:
                return "service&search_files&Petrov";
            case 3:
                return editFile();
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

    @Override //TODO: доделать весь интерфейс редактирования файла
    public String editFile() {
        StringBuilder request;
        File file;
        ClientUserSession userSession;
        ClientService clientService;

        clientService = ServiceProvider.getInstance().getClientService();

        request = new StringBuilder("service&edit_file&");
        userSession = ClientUserSession.getInstance();
        file = userSession.getFiles().get(consoleDataService
                .getNumFromConsole("Enter file's ID: ", 1, userSession.getFiles().size()) - 1);

        file.getStudent().setFirstName("Test done!");
        request.append(clientService.getAllFiles());

        return request.toString();
    }
}
