package by.epam.library.controller.impl;

import by.epam.library.bean.Library;
import by.epam.library.controller.Controller;
import by.epam.library.service.LibraryService;
import by.epam.library.service.ServiceProvider;
import by.epam.library.service.UserBaseService;
import by.epam.library.service.UserService;

public class ServiceController implements Controller {
    private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
    private final LibraryService libraryService = serviceProvider.getLibraryService();
    private final UserBaseService userBaseService = serviceProvider.getUserBaseService();
    private final UserService userService = serviceProvider.getUserService();
    private final Library library = Library.getInstance();


    @Override
    public void doAction(String request) {
        switch (request) {
            case "add_book":
                libraryService.addBook();
            case "edit_book":
                libraryService.editBook();
            case "search_book":
                libraryService.searchBooksByKeyword();
            case "add_user":
                userBaseService.addUser();
            case "suggest_book":
                userService.suggestNewBook(library.getAuthorizedUser());
        }
    }
}
