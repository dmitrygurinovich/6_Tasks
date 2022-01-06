package by.epam.library.command.impl;

import by.epam.library.command.Command;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.presentation.View;

public class ShowBooksCommand implements Command {
    @Override
    public void execute() {
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        View view = presentationProvider.getView();

        view.showBooks();
    }
}