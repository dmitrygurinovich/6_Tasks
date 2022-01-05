package by.epam.library.command.impl;

import by.epam.library.command.Command;
import by.epam.library.service.LibraryService;
import by.epam.library.service.ServiceProvider;

public class SearchBooksByKeywordCommand implements Command {
    private final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private final LibraryService LIBRARY_SERVICE = SERVICE_PROVIDER.getLibraryService();

    @Override
    public void execute() {
        LIBRARY_SERVICE.searchBooksByKeyword();
    }
}
