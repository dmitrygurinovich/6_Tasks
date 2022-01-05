package by.epam.library.command.impl;

import by.epam.library.command.Command;
import by.epam.library.service.ServiceProvider;
import by.epam.library.service.UserBaseService;

public class AddUserCommand implements Command {
    private final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private final UserBaseService USER_BASE_SERVICE = SERVICE_PROVIDER.getUserBaseService();

    @Override
    public void execute() {
        USER_BASE_SERVICE.addUser();
    }
}
