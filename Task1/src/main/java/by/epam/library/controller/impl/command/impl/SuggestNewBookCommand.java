package by.epam.library.controller.impl.command.impl;

import by.epam.library.bean.Library;
import by.epam.library.bean.User;
import by.epam.library.controller.impl.command.Command;
import by.epam.library.service.ServiceProvider;
import by.epam.library.service.UserService;

public class SuggestNewBookCommand implements Command {
    @Override
    public void execute() {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        Library library = Library.getInstance();
        User authorizedUser = library.getAuthorizedUser();

        userService.suggestNewBook(authorizedUser);
    }
}
