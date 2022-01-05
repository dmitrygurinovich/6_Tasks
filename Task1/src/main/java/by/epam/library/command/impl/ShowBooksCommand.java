package by.epam.library.command.impl;

import by.epam.library.command.Command;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.presentation.View;

public class ShowBooksCommand implements Command {
    private final PresentationProvider PRESENTATION_PROVIDER = PresentationProvider.getInstance();
    private final View VIEW = PRESENTATION_PROVIDER.getView();

    @Override
    public void execute() {
        VIEW.showBooks();
    }
}