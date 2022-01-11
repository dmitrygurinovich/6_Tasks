package by.epam.library.controller.impl.command.impl;

import by.epam.library.controller.impl.command.Command;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.presentation.UserInterface;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        UserInterface userInterface = presentationProvider.getUserInterface();

        userInterface.exit();
    }
}
