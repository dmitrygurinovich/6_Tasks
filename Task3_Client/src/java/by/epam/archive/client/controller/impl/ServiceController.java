package by.epam.archive.client.controller.impl;

import by.epam.archive.client.command.ClientCommandProvider;
import by.epam.archive.client.controller.ClientController;

public class ServiceController implements ClientController {
    ClientCommandProvider commandProvider = ClientCommandProvider.getInstance();

    @Override
    public String action(String request) {
        String[] params;

        params = request.split("&");

        return commandProvider.getCommand(params[1]).execute(request);
    }
}
