package by.epam.archive.server.controller.impl;

import by.epam.archive.server.command.ServerCommandProvider;
import by.epam.archive.server.controller.ServerController;

public class ServiceController implements ServerController {
    private final ServerCommandProvider serverCommandProvider = ServerCommandProvider.getInstance();

    @Override
    public String action(String request) {
        String[] params = request.split("&");

        return params[0].concat("&").concat(serverCommandProvider.getCommand(params[1]).execute(request));
    }
}
