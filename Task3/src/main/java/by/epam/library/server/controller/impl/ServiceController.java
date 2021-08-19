package by.epam.library.server.controller.impl;

import by.epam.library.server.command.CommandProvider;
import by.epam.library.server.controller.Controller;

public class ServiceController implements Controller {
    private final CommandProvider commandProvider = new CommandProvider();

    @Override
    public void action(String request) {
        commandProvider.getCommand(request).execute();
    }
}
