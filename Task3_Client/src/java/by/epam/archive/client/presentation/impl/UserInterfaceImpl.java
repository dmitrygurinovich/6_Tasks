package by.epam.archive.client.presentation.impl;

import by.epam.archive.client.bean.ClientUserSession;
import by.epam.archive.client.bean.File;
import by.epam.archive.client.bean.Student;
import by.epam.archive.client.bean.Subject;
import by.epam.archive.client.presentation.UserInterface;
import by.epam.archive.client.service.ClientService;
import by.epam.archive.client.service.ConsoleDataService;
import by.epam.archive.client.service.ServiceProvider;

public class UserInterfaceImpl implements UserInterface {
    private static final ConsoleDataService CONSOLE_DATA = ServiceProvider.getInstance().getConsoleDataService();
    private final ClientService CLIENT_SERVICE = ServiceProvider.getInstance().getClientService();
    private final ClientUserSession userSession = ClientUserSession.getInstance();
    private static int defaultFileId = -1;

    @Override
    public String adminMenu() {
        int menuItem;

        menuItem = CONSOLE_DATA.getNumFromConsole("" +
                        "|+++ ADMIN MENU +++|\n" +
                        "1. Show all files\n" +
                        "2. Search by keyword\n" +
                        "3. Edit file\n" +
                        "4. Add file\n" +
                        "5. Delete file\n" +
                        "0. Log out\n",
                0, 5);

        switch (menuItem) {
            case 1:
                return "service&show_all_files";
            case 2:
                return getSearchFilesRequest();
            case 3:
                return getEditFileRequest();
            case 4:
                return getAddFileRequest();
            case 5:
                return getDeleteFileRequest();
            case 0:
                return "authorization&log_out";
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
                        "2. Search by keyword\n" +
                        "0. Log out\n",
                0, 2);

        switch (menuItem) {
            case 1:
                return "service&show_all_files";
            case 2:
                return getSearchFilesRequest();
            case 0:
                return "authorization&log_out";
            default:
                return "Invalid command!";
        }
    }

    public String getAuthorizationRequest() {
        StringBuilder authorizationRequest;

        authorizationRequest = new StringBuilder("authorization&");

        authorizationRequest.append(CONSOLE_DATA.getStringFromConsole("|+++ AUTHORIZATION +++|\nEnter user name:")).append("&");
        authorizationRequest.append(CONSOLE_DATA.getStringFromConsole("Enter password:"));

        return authorizationRequest.append("&server_req").toString();
    }

    private String getSearchFilesRequest() {
        if (userSession.getFiles().isEmpty()) {
            return "error&no_files";
        }
        StringBuilder request;

        request = new StringBuilder("service&search_files&");
        request.append(CONSOLE_DATA.getStringFromConsole("Enter a keyword for searching:"));

        return request.toString();
    }

    private String getAddFileRequest() {
        ClientUserSession userSession;
        Student student;
        File file;
        int menuItem;
        StringBuilder request;

        student = new Student();
        userSession = ClientUserSession.getInstance();
        request = new StringBuilder("service&add_file&");

        student.setFirstName(CONSOLE_DATA.getStringFromConsole("Enter first name:"));
        student.setSecondName(CONSOLE_DATA.getStringFromConsole("Enter second name:"));
        student.setAge(CONSOLE_DATA.getNumFromConsole("Enter student's age", 17, 30));
        student.setGroupNumber(CONSOLE_DATA.getNumFromConsole("Enter group number:", 1, 3));

        file = new File(student);
        file.setId(userSession.getFiles().size() + 1);

        menuItem = CONSOLE_DATA.getNumFromConsole("" +
                "Would you like to add student's progress?\n" +
                "1. Yes\n" +
                "2. No\n", 1, 2);

        if (menuItem == 1) {

            while (menuItem != 0) {
                menuItem = CONSOLE_DATA.getNumFromConsole("" +
                        "Add progress:\n" +
                        "1. Math\n" +
                        "2. Physics\n" +
                        "3. English\n" +
                        "4. Geography\n" +
                        "5. Literature\n" +
                        "0. Save and add file to base", 0, 5);

                switch (menuItem) {
                    case 1:
                        file.getProgress().put(Subject.MATH, CONSOLE_DATA.getNumFromConsole("Math:", 1, 5));
                        break;
                    case 2:
                        file.getProgress().put(Subject.PHYSICS, CONSOLE_DATA.getNumFromConsole("Physics:", 1, 5));
                        break;
                    case 3:
                        file.getProgress().put(Subject.ENGLISH, CONSOLE_DATA.getNumFromConsole("English", 1, 5));
                        break;
                    case 4:
                        file.getProgress().put(Subject.GEOGRAPHY, CONSOLE_DATA.getNumFromConsole("Geography", 1, 5));
                        break;
                    case 5:
                        file.getProgress().put(Subject.LITERATURE, CONSOLE_DATA.getNumFromConsole("Literature", 1, 5));
                        break;
                }
            }
        }

        userSession.getFiles().add(file);

        return request.append(CLIENT_SERVICE.getAllFilesInXml()).append("&server_req").toString();
    }

    private String getEditFileRequest() {
        if (userSession.getFiles().isEmpty()) {
            return "error&no_files";
        }

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
            defaultFileId = CONSOLE_DATA.getNumFromConsole("Enter file's number:", 1, userSession.getFiles().size()) - 1;
        }

        fileId = defaultFileId;

        file = userSession.getFiles().get(fileId);

        menuItem = CONSOLE_DATA.getNumFromConsole("" +
                "1. Edit first name\n" +
                "2. Edit second name\n" +
                "3. Edit age\n" +
                "4. Edit group number\n" +
                "5. Edit progress\n" +
                "0. Exit to the main menu", 0, 5
        );

        switch (menuItem) {

            case 1:
                file.getStudent().setFirstName(CONSOLE_DATA.getStringFromConsole("Enter new first name:"));
                break;
            case 2:
                file.getStudent().setSecondName(CONSOLE_DATA.getStringFromConsole("Enter new second name:"));
                break;
            case 3:
                file.getStudent().setAge(CONSOLE_DATA.getNumFromConsole("Enter new age:", 17, 40));
                break;
            case 4:
                file.getStudent().setGroupNumber(CONSOLE_DATA.getNumFromConsole("Enter new group number:", 1, 5));
                break;
            case 5:
                getFileProgress(file);
                break;
            case 0:
                request.append(clientService.getAllFilesInXml());
                return request.toString().concat("&server_req");
        }

        return getEditFileRequest();
    }

    private File getFileProgress(File file) {
        int menuItem;

        menuItem = CONSOLE_DATA.getNumFromConsole("" +
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
                file.getProgress().put(Subject.MATH, CONSOLE_DATA.getNumFromConsole("Math:", 1, 5));
                break;
            case 2:
                file.getProgress().put(Subject.PHYSICS, CONSOLE_DATA.getNumFromConsole("Physics:", 1, 5));
                break;
            case 3:
                file.getProgress().put(Subject.ENGLISH, CONSOLE_DATA.getNumFromConsole("English:", 1, 5));
                break;
            case 4:
                file.getProgress().put(Subject.GEOGRAPHY, CONSOLE_DATA.getNumFromConsole("Geography:", 1, 5));
                break;
            case 5:
                file.getProgress().put(Subject.LITERATURE, CONSOLE_DATA.getNumFromConsole("Literature:", 1, 5));
                break;
            case 0:
                return file;
        }

        return getFileProgress(file);
    }

    private String getDeleteFileRequest() {
        int fileId;
        ClientUserSession userSession;
        StringBuilder request;

        userSession = ClientUserSession.getInstance();
        request = new StringBuilder("service&delete_file&");
        fileId = CONSOLE_DATA.getNumFromConsole("Enter file's ID:", 1, userSession.getFiles().size()) - 1;

        CLIENT_SERVICE.deleteFile(fileId);

        return request.append(CLIENT_SERVICE.getAllFilesInXml()).append("&server_req").toString();
    }
}
