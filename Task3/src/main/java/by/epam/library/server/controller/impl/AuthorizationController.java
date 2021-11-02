package by.epam.library.server.controller.impl;

import by.epam.library.server.controller.Controller;

public class AuthorizationController implements Controller {
    @Override
    public String action(String request) {
        String[] params = request.split("\\s+");

        String username = params[0];
        String password = params[1];

        // авторизация

        return "authorized!";

    }
}
