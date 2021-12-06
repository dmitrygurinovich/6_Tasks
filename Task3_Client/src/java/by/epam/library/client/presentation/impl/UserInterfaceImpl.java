package by.epam.library.client.presentation.impl;

import by.epam.library.client.bean.ClientUserSession;
import by.epam.library.client.bean.File;
import by.epam.library.client.bean.Subject;
import by.epam.library.client.bean.UserRole;
import by.epam.library.client.presentation.PresentationProvider;
import by.epam.library.client.presentation.UserInterface;
import by.epam.library.client.presentation.View;
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
        int menuItem;

        request = new StringBuilder("service&edit_file&");
        clientService = ServiceProvider.getInstance().getClientService();
        userSession = ClientUserSession.getInstance();


        file = userSession.getFiles().get(consoleDataService.getNumFromConsole("Enter file's number:", 1, userSession.getFiles().size()) - 1);

        menuItem = consoleDataService.getNumFromConsole("" +
                "1. Edit first name\n" +
                "2. Edit second name\n" +
                "3. Edit age\n" +
                "4. Edit group number\n" +
                "5. Edit progress\n" +
                "0. Exit to the main menu", 0, 4);

        switch (menuItem) {
            case 0:
                //break;
                if (userSession.getUser().getRole().equals(UserRole.ADMINISTRATOR)) {
                    adminMenu();
                } else {
                    userMenu();
                }
            case 1:
                file.getStudent().setFirstName(consoleDataService.getStringFromConsole("Enter new first name:"));
            case 2:
                file.getStudent().setSecondName(consoleDataService.getStringFromConsole("Enter new second name:"));
            case 3:
                file.getStudent().setAge(consoleDataService.getNumFromConsole("Enter new age:", 17, 40));
            case 4:
                file.getStudent().setGroupNumber(consoleDataService.getNumFromConsole("Enter new group number:", 1, 5));
            case 5:
                editProgress(file);
            default:
                editFile();
        }

        request.append(clientService.getAllFiles());

        return request.toString();
    }

    public void editProgress(File file) {
        int menuItem;
        View view;

        menuItem = consoleDataService.getNumFromConsole("" +
                "Choose subject:\n" +
                "1. Math\n" +
                "2. Physics\n" +
                "3. English\n" +
                "4. Geography\n" +
                "5. Literature\n", 1,1 );
        view = PresentationProvider.getInstance().getVIEW();

        switch (menuItem) {
            case 1:
                file.getProgress().put(Subject.MATH, consoleDataService.getNumFromConsole("", 1, 5));
                editProgress(file);
            case 2:
                file.getProgress().put(Subject.PHYSICS, consoleDataService.getNumFromConsole("",1, 5));
                editProgress(file);
            case 3:
                file.getProgress().put(Subject.ENGLISH, consoleDataService.getNumFromConsole("", 1, 5));
                editProgress(file);
            case 4:
                file.getProgress().put(Subject.GEOGRAPHY, consoleDataService.getNumFromConsole("", 1, 5));
                editProgress(file);
            case 5:
                file.getProgress().put(Subject.LITERATURE, consoleDataService.getNumFromConsole("",1, 5));
                editProgress(file);
            case 0:
                editFile();
            default:
                view.print("Enter number 0 - 5!");
                editProgress(file);
        }
    }
}
