package by.epam.archive.client.command.impl;

import by.epam.archive.client.presentation.PresentationProvider;
import by.epam.archive.client.presentation.View;
import by.epam.archive.client.bean.ClientUserSession;
import by.epam.archive.client.bean.User;
import by.epam.archive.client.bean.UserRole;
import by.epam.archive.client.command.ClientCommand;

public class LogInCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        String[] params;
        ClientUserSession clientUserSession;
        View view;

        params = request.split("&");
        clientUserSession = ClientUserSession.getInstance();
        view = PresentationProvider.getInstance().getVIEW();

        clientUserSession.setAuthorized(true);
        clientUserSession.setUser(new User(params[2], "", (params[3].equals("Administrator")) ? UserRole.ADMINISTRATOR : UserRole.USER));
        view.print("\nUser " + clientUserSession.getUser().getUsername() + " is authorized!\n");

        return "authorized";
    }
}
