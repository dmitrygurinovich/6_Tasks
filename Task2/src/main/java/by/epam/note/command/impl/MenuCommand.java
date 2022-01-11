package by.epam.note.command.impl;

import by.epam.note.command.Command;
import by.epam.note.presentation.PresentationProvider;
import by.epam.note.presentation.UserInterface;

public class MenuCommand implements Command {
    @Override
    public void execute() {
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        UserInterface userInterface = presentationProvider.getUserInterface();

        userInterface.menu();
    }
}
