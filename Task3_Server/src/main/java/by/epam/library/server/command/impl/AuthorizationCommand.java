package by.epam.library.server.command.impl;

import by.epam.library.server.command.ServerCommand;
import by.epam.library.server.dao.DAOProvider;
import by.epam.library.server.dao.UsersBaseDAO;
import by.epam.library.server.view.View;
import by.epam.library.server.view.impl.ViewImpl;

public class AuthorizationCommand implements ServerCommand {
    @Override
    public String execute(String request) {
        String[] params;
        UsersBaseDAO usersBaseDAO;
        StringBuilder authorizationResponse;
        View view;

        params = request.split("&");
        usersBaseDAO = DAOProvider.getInstance().getUsersBaseDAO();
        authorizationResponse = new StringBuilder("authorization&log_in&");
        view = ViewImpl.getInstance();

        String username = params[1];
        String password = params[2];

        if (usersBaseDAO.getUsers().containsKey(params[1])) {

            if (usersBaseDAO.getUsers().get(username).getPassword().equals(password)) {
                view.print("#Server: " + username + " is authorized.");
                return authorizationResponse
                        .append(usersBaseDAO.getUsers().get(params[1]).getUsername())
                        .append("&")
                        .append(usersBaseDAO.getUsers().get(params[1]).getRole().toString())
                        .toString();
            }
        }
        return "authorization&filed";
    }
}
