package by.epam.library.command.impl;

import by.epam.library.command.Command;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.presentation.View;

public class ShowBooksCommand implements Command {
    private final View view = PresentationProvider.getInstance().getView();

    @Override
    public void execute() {
        view.showBooks();
    }
}
