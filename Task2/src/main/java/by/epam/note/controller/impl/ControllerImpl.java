package by.epam.note.controller.impl;

import by.epam.note.command.CommandProvider;
import by.epam.note.controller.Controller;

public class ControllerImpl implements Controller {
    private final CommandProvider COMMAND_PROVIDER = CommandProvider.getInstance();
    private static ControllerImpl instance;

    private ControllerImpl() {

    }

    public static ControllerImpl getInstance() {
        if (instance == null) {
            instance = new ControllerImpl();
        }
        return instance;
    }

    @Override
    public void doAction(String request) {
        COMMAND_PROVIDER.getCommand(request).execute();
    }
}
