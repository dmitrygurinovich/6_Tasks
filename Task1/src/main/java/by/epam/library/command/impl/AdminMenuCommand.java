package by.epam.library.command.impl;

import by.epam.library.command.Command;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.presentation.UserInterface;

public class AdminMenuCommand implements Command {
    @Override
    public void execute() {
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        UserInterface userInterface = presentationProvider.getUserInterface();

        userInterface.adminMenu();
    }
}
