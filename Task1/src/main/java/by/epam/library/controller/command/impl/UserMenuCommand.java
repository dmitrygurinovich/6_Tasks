package by.epam.library.controller.command.impl;

import by.epam.library.controller.command.Command;
import by.epam.library.presentation.UserInterface;
import by.epam.library.presentation.ViewProvider;

public class UserMenuCommand implements Command {
    private UserInterface ui = ViewProvider.getInstance().getUserInterface();

    @Override
    public void execute() {
        ui.userMenu();
    }
}
