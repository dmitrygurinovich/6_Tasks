package by.epam.library.client.command.impl;

import by.epam.library.client.command.ClientCommand;
import by.epam.library.client.presentation.PresentationProvider;
import by.epam.library.client.presentation.UserInterface;

public class AuthorizationCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        UserInterface userInterface;

        userInterface = PresentationProvider.getInstance().getUSER_INTERFACE();

        return userInterface.getAuthorizationRequest();
    }
}
