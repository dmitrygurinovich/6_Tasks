package by.epam.archive.client.command.impl;

import by.epam.archive.client.presentation.PresentationProvider;
import by.epam.archive.client.presentation.View;
import by.epam.archive.client.command.ClientCommand;

public class AuthorizationFailedCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        View view;

        view = PresentationProvider.getInstance().getVIEW();

        view.print("Wrong username or password!");
        return request;
    }
}
