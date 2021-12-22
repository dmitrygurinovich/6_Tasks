package by.epam.archive.server.command.impl;

import by.epam.archive.server.service.FileBaseService;
import by.epam.archive.server.service.ServiceProvider;
import by.epam.archive.server.command.ServerCommand;

public class GetAllFilesCommand implements ServerCommand {
    private final FileBaseService fileBaseService = ServiceProvider.getInstance().getFileBaseService();

    @Override
    public String execute(String request) {
        String[] params;
        params = request.split("&");
        return params[1].concat("&").concat(fileBaseService.getAllFiles());
    }
}
