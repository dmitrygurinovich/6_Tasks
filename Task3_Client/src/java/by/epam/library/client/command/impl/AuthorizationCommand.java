package by.epam.library.client.command.impl;

import by.epam.library.client.command.ClientCommand;
import by.epam.library.client.service.ConsoleDataService;
import by.epam.library.client.service.ServiceProvider;

public class AuthorizationCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        ConsoleDataService consoleDataService;
        StringBuilder authorizationRequest;

        consoleDataService = ServiceProvider.getInstance().getConsoleDataService();
        authorizationRequest = new StringBuilder("authorization&");

        authorizationRequest.append(consoleDataService.getStringFromConsole("|+++ AUTHORIZATION +++|\nEnter user name:")).append("&");
        authorizationRequest.append(consoleDataService.getStringFromConsole("Enter password:"));

        return authorizationRequest.toString();
    }
}
