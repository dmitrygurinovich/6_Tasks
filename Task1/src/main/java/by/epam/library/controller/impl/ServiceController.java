package by.epam.library.controller.impl;

import by.epam.library.controller.Controller;
import by.epam.library.controller.command.CommandProvider;

public class ServiceController implements Controller {
    private final CommandProvider commandProvider = new CommandProvider();

    @Override
    public void doAction(String request) {
        commandProvider.getCommand(request).execute();
    }
}
