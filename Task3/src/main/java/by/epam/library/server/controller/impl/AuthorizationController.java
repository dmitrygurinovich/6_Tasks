package by.epam.library.server.controller.impl;

import by.epam.library.server.controller.ServerController;

public class AuthorizationController implements ServerController {
    @Override
    public String action(String request) {
        String[] params = request.split("&");

        String username = params[0];
        String password = params[1];

        // авторизация

        return "authorized!";

    }
}
