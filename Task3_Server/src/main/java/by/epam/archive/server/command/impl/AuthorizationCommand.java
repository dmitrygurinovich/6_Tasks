package by.epam.archive.server.command.impl;

import by.epam.archive.server.command.ServerCommand;
import by.epam.archive.server.service.ServiceProvider;
import by.epam.archive.server.service.UserBaseService;

public class AuthorizationCommand implements ServerCommand {
    @Override
    public String execute(String request) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserBaseService userBaseService = serviceProvider.getUserBaseService();

        return userBaseService.authorization(request);
    }
}
