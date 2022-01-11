package by.epam.library.controller.impl.command.impl;

import by.epam.library.controller.impl.command.Command;
import by.epam.library.service.LibraryService;
import by.epam.library.service.ServiceProvider;

public class SearchBooksByKeywordCommand implements Command {
    @Override
    public void execute() {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        LibraryService libraryService = serviceProvider.getLibraryService();

        libraryService.searchBooksByKeyword();
    }
}
