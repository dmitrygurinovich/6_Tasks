package by.epam.archive.client.command.impl;

import by.epam.archive.client.bean.ClientUserSession;
import by.epam.archive.client.command.ClientCommand;

public class LogOutCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        ClientUserSession clientUserSession;

        clientUserSession = ClientUserSession.getInstance();

        clientUserSession.setAuthorized(false);

        return request;
    }
}
