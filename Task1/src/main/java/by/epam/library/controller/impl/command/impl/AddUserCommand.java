package by.epam.library.controller.impl.command.impl;

import by.epam.library.controller.impl.command.Command;
import by.epam.library.service.ServiceProvider;
import by.epam.library.service.UserBaseService;

public class AddUserCommand implements Command {
    @Override
    public void execute() {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserBaseService userBaseService = serviceProvider.getUserBaseService();

        userBaseService.addUser();
    }
}
