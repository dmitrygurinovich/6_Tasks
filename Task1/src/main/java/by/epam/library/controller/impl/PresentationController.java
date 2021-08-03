package by.epam.library.controller.impl;

import by.epam.library.command.CommandProvider;
import by.epam.library.controller.Controller;

public class PresentationController implements Controller {
    private final CommandProvider commandProvider = new CommandProvider();

    @Override
    public void doAction(String request) {
        commandProvider.getCommand(request).execute();
    }
}
