package by.epam.library.client.presentation.impl;

import by.epam.library.client.bean.ClientUserSession;
import by.epam.library.client.bean.File;
import by.epam.library.client.bean.Subject;
import by.epam.library.client.presentation.UserInterface;
import by.epam.library.client.service.ClientService;
import by.epam.library.client.service.ConsoleDataService;
import by.epam.library.client.service.ServiceProvider;

public class UserInterfaceImpl implements UserInterface {
    private static final ConsoleDataService consoleDataService = ServiceProvider.getInstance().getConsoleDataService();
    private static int defaultFileId = -1;

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

    @Override
    public String editFile() {
        StringBuilder request;
        File file;
        ClientUserSession userSession;
        ClientService clientService;
        int menuItem;
        int fileId;

        request = new StringBuilder("service&edit_file&");
        clientService = ServiceProvider.getInstance().getClientService();
        userSession = ClientUserSession.getInstance();

        if (defaultFileId < 0) {
            defaultFileId = consoleDataService.getNumFromConsole("Enter file's number:", 1, userSession.getFiles().size()) - 1;
        }

        fileId = defaultFileId;

        file = userSession.getFiles().get(fileId);

        menuItem = consoleDataService.getNumFromConsole("" +
                "1. Edit first name\n" +
                "2. Edit second name\n" +
                "3. Edit age\n" +
                "4. Edit group number\n" +
                "5. Edit progress\n" +
                "0. Exit to the main menu", 0, 5);

        switch (menuItem) {

            case 1:
                file.getStudent().setFirstName(consoleDataService.getStringFromConsole("Enter new first name:"));
                break;
            case 2:
                file.getStudent().setSecondName(consoleDataService.getStringFromConsole("Enter new second name:"));
                break;
            case 3:
                file.getStudent().setAge(consoleDataService.getNumFromConsole("Enter new age:", 17, 40));
                break;
            case 4:
                file.getStudent().setGroupNumber(consoleDataService.getNumFromConsole("Enter new group number:", 1, 5));
                break;
            case 5:
                editProgress(file);
            case 0:
                request.append(clientService.getAllFiles());
                return request.toString();
        }

        return editFile();
    }

    public void editProgress(File file) {
        int menuItem;

        menuItem = consoleDataService.getNumFromConsole("" +
                "Choose subject:\n" +
                "1. Math\n" +
                "2. Physics\n" +
                "3. English\n" +
                "4. Geography\n" +
                "5. Literature\n" +
                "0. To the file's editing menu\n", 0, 5
        );

        switch (menuItem) {
            case 1:
                file.getProgress().put(Subject.MATH, consoleDataService.getNumFromConsole("Math:", 1, 5));
                break;
            case 2:
                file.getProgress().put(Subject.PHYSICS, consoleDataService.getNumFromConsole("Physics:", 1, 5));
                break;
            case 3:
                file.getProgress().put(Subject.ENGLISH, consoleDataService.getNumFromConsole("English:", 1, 5));
                break;
            case 4:
                file.getProgress().put(Subject.GEOGRAPHY, consoleDataService.getNumFromConsole("Geography:", 1, 5));
                break;
            case 5:
                file.getProgress().put(Subject.LITERATURE, consoleDataService.getNumFromConsole("Literature:", 1, 5));
                break;
            case 0:
                editFile();
        }
        editProgress(file);
    }
}
