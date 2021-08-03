package by.epam.library.controller.command.impl;

import by.epam.library.controller.command.Command;
import by.epam.library.service.LibraryService;
import by.epam.library.service.ServiceProvider;

public class SearchBooksByKeywordCommand implements Command {
    private final LibraryService libraryService = ServiceProvider.getInstance().getLibraryService();

    @Override
    public void execute() {
        libraryService.searchBooksByKeyword();
    }
}
