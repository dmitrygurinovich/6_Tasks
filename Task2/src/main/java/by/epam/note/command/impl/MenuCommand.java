package by.epam.note.command.impl;

import by.epam.note.command.Command;
import by.epam.note.presentation.PresentationProvider;
import by.epam.note.presentation.UserInterface;

public class MenuCommand implements Command {
    private final UserInterface userInterface = PresentationProvider.getInstance().getUserInterface();

    @Override
    public void execute() {
        userInterface.menu();
    }
}
