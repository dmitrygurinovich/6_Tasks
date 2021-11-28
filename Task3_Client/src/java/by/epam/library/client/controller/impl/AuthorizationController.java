package by.epam.library.client.controller.impl;

import by.epam.library.client.command.ClientCommandProvider;
import by.epam.library.client.controller.ClientController;

public class AuthorizationController implements ClientController {
    @Override
    public String action(String request) {
        String[] params;
        ClientCommandProvider commandProvider;

        params = request.split("&");
        commandProvider = ClientCommandProvider.getInstance();

        return commandProvider.getCommand(params[1]).execute(request);
    }
}
