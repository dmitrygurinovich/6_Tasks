package by.epam.library.client.command.impl;

import by.epam.library.client.bean.ClientUserSession;
import by.epam.library.client.bean.User;
import by.epam.library.client.bean.UserRole;
import by.epam.library.client.command.ClientCommand;
import by.epam.library.client.presentation.PresentationProvider;
import by.epam.library.client.presentation.View;

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
        view.print("User " + clientUserSession.getUser().getUsername() + " is authorized!");

        return "authorized";
    }
}
