package by.epam.library.controller.impl;

import by.epam.library.controller.impl.command.Command;
import by.epam.library.controller.impl.command.CommandProvider;
import by.epam.library.controller.Controller;

public class PresentationController implements Controller {

    @Override
    public void doAction(String request) {
        CommandProvider commandProvider = CommandProvider.getInstance();
        Command command = commandProvider.getCommand(request);

        command.execute();
    }
}
