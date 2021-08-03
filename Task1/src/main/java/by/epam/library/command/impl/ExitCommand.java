package by.epam.library.command.impl;

import by.epam.library.command.Command;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.presentation.UserInterface;

public class ExitCommand implements Command {
    private final UserInterface userInterface = PresentationProvider.getInstance().getUserInterface();

    @Override
    public void execute() {
        userInterface.exit();
    }
}
