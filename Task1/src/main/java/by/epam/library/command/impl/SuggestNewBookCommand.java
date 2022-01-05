package by.epam.library.command.impl;

import by.epam.library.bean.Library;
import by.epam.library.bean.User;
import by.epam.library.command.Command;
import by.epam.library.service.ServiceProvider;
import by.epam.library.service.UserService;

public class SuggestNewBookCommand implements Command {
    private final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private final UserService USER_SERVICE = SERVICE_PROVIDER.getUserService();
    private final Library LIBRARY = Library.getInstance();
    private final User AUTHORIZED_USER = LIBRARY.getAuthorizedUser();

    @Override
    public void execute() {
        USER_SERVICE.suggestNewBook(AUTHORIZED_USER);
    }
}
