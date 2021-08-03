package by.epam.library.command.impl;

import by.epam.library.command.Command;
import by.epam.library.service.ServiceProvider;
import by.epam.library.service.UserBaseService;

public class AddUserCommand implements Command {
    private final UserBaseService userBaseService = ServiceProvider.getInstance().getUserBaseService();

    @Override
    public void execute() {
        userBaseService.addUser();
    }
}
