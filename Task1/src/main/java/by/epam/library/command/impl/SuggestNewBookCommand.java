package by.epam.library.command.impl;

import by.epam.library.bean.Library;
import by.epam.library.bean.User;
import by.epam.library.command.Command;
import by.epam.library.service.ServiceProvider;
import by.epam.library.service.UserService;

public class SuggestNewBookCommand implements Command {
    private final UserService userService = ServiceProvider.getInstance().getUserService();
    private final User authorizedUser = Library.getInstance().getAuthorizedUser();

    @Override
    public void execute() {
        userService.suggestNewBook(authorizedUser);
    }
}
