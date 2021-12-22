package by.epam.archive.client.command.impl;

import by.epam.archive.client.presentation.PresentationProvider;
import by.epam.archive.client.presentation.UserInterface;
import by.epam.archive.client.command.ClientCommand;

public class AuthorizationCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        UserInterface userInterface;

        userInterface = PresentationProvider.getInstance().getUSER_INTERFACE();

        return userInterface.getAuthorizationRequest();
    }
}
