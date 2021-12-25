package by.epam.archive.client.controller.impl;

import by.epam.archive.client.command.ClientCommandProvider;
import by.epam.archive.client.controller.ClientController;

public class ErrorController implements ClientController {
    @Override
    public String action(String request) {
        String[] params;
        ClientCommandProvider commandProvider;

        params = request.split("&");
        commandProvider = ClientCommandProvider.getInstance();

        return commandProvider.getCommand(params[1]).execute(request);
    }
}
