package client.controller.impl;

import client.command.ClientCommandProvider;
import client.controller.ClientController;

public class ServiceController implements ClientController {
    ClientCommandProvider commandProvider = ClientCommandProvider.getInstance();

    @Override
    public String action(String request) {
        String[] params;

        params = request.split("&");

        return commandProvider.getCommand(params[1]).execute(request);
    }
}
