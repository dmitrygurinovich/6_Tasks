package by.epam.library.client.command.impl;

import by.epam.library.client.bean.ClientUserSession;
import by.epam.library.client.bean.UserRole;
import by.epam.library.client.command.ClientCommand;
import by.epam.library.client.presentation.PresentationProvider;
import by.epam.library.client.presentation.UserInterface;

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
