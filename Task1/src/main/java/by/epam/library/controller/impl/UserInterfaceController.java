package by.epam.library.controller.impl;

import by.epam.library.controller.Controller;
import by.epam.library.controller.command.Command;
import by.epam.library.controller.command.CommandProvider;

public class UserInterfaceController implements Controller {
    private CommandProvider provider = new CommandProvider();

    @Override
    public void doAction(String request) {
        Command currentCommand = provider.getCommand(request);
        currentCommand.execute();
    }

}
