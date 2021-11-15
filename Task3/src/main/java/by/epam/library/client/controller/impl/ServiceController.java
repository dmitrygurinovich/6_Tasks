package by.epam.library.client.controller.impl;

import by.epam.library.client.command.ClientCommandProvider;
import by.epam.library.client.controller.ClientController;

public class ServiceController implements ClientController {
    ClientCommandProvider commandProvider = ClientCommandProvider.getInstance();

    @Override
    public String action(String request) {
     return null;
    }
}
