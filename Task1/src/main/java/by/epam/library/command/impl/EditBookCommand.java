package by.epam.library.command.impl;

import by.epam.library.command.Command;
import by.epam.library.service.LibraryService;
import by.epam.library.service.ServiceProvider;

public class EditBookCommand implements Command {
    @Override
    public void execute() {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        LibraryService libraryService = serviceProvider.getLibraryService();

        libraryService.editBook();
    }
}
