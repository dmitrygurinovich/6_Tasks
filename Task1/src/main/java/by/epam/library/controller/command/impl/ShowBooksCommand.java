package by.epam.library.controller.command.impl;

import by.epam.library.controller.command.Command;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.presentation.View;

public class ShowBooksCommand implements Command {
    private final View view = PresentationProvider.getInstance().getView();

    @Override
    public void execute() {
        view.showBooks();
    }
}
