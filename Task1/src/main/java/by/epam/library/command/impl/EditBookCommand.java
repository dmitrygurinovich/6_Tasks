package by.epam.library.command.impl;

import by.epam.library.command.Command;
import by.epam.library.service.LibraryService;
import by.epam.library.service.ServiceProvider;

public class EditBookCommand implements Command {
    private final LibraryService libraryService = ServiceProvider.getInstance().getLibraryService();

    @Override
    public void execute() {
        libraryService.editBook();
    }
}
