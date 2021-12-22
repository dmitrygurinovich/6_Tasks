package by.epam.archive.server.controller.impl;

import by.epam.archive.server.command.ServerCommandProvider;
import by.epam.archive.server.controller.ServerController;

public class AuthorizationController implements ServerController {
    @Override
    public String action(String request) {
        ServerCommandProvider commandProvider;
        String[] params;

        commandProvider = ServerCommandProvider.getInstance();
        params = request.split("&");

        return commandProvider.getCommand(params[0]).execute(request);
    }
}
