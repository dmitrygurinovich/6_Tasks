package by.epam.library.controller.impl;

import by.epam.library.command.Command;
import by.epam.library.command.CommandProvider;
import by.epam.library.controller.Controller;

public class ServiceController implements Controller {
    private final CommandProvider COMMAND_PROVIDER = new CommandProvider();

    @Override
    public void doAction(String request) {
        Command command = COMMAND_PROVIDER.getCommand(request);
        command.execute();
    }
}
