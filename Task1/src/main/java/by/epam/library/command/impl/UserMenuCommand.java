package by.epam.library.command.impl;

import by.epam.library.command.Command;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.presentation.UserInterface;

public class UserMenuCommand implements Command {
    private final PresentationProvider PRESENTATION_PROVIDER = PresentationProvider.getInstance();
    private final UserInterface USER_INTERFACE = PRESENTATION_PROVIDER.getUserInterface();

    @Override
    public void execute() {
        USER_INTERFACE.userMenu();
    }
}