package by.epam.library.client.controller.impl;

import by.epam.library.client.command.ClientCommandProvider;
import by.epam.library.client.controller.ClientController;

public class MenuController implements ClientController {
    @Override
    public String action(String request) {
        ClientCommandProvider clientCommand;

        clientCommand = ClientCommandProvider.getInstance();

        return clientCommand.getCommand(request).execute(request);
    }
}
