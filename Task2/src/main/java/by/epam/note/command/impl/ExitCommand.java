package by.epam.note.command.impl;

import by.epam.library.presentation.PresentationProvider;
import by.epam.library.presentation.UserInterface;
import by.epam.note.command.Command;

public class ExitCommand implements Command {

    @Override
    public void execute() {
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        UserInterface userInterface = presentationProvider.getUserInterface();

        userInterface.exit();
    }
}
