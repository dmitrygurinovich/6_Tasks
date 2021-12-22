package by.epam.archive.client.command.impl;

import by.epam.archive.client.presentation.PresentationProvider;
import by.epam.archive.client.presentation.UserInterface;
import by.epam.archive.client.bean.ClientUserSession;
import by.epam.archive.client.bean.UserRole;
import by.epam.archive.client.command.ClientCommand;

public class GetMenuCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        UserInterface userInterface;
        ClientUserSession clientUserSession;

        userInterface = PresentationProvider.getInstance().getUSER_INTERFACE();
        clientUserSession = ClientUserSession.getInstance();

        if (clientUserSession.getUser().getRole().equals(UserRole.ADMINISTRATOR)) {
            return userInterface.adminMenu();
        } else {
            return userInterface.userMenu();
        }
    }
}
