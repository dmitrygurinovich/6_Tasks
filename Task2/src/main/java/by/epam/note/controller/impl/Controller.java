package by.epam.note.controller.impl;

import by.epam.note.command.CommandProvider;

public class Controller implements by.epam.note.controller.Controller {
    private final CommandProvider commandProvider = new CommandProvider();
    private static Controller instance;

    private Controller() {}

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    @Override
    public void doAction(String request) {
        commandProvider.getCommand(request).execute();
    }
}
