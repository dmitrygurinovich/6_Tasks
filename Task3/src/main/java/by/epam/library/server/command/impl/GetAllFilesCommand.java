package by.epam.library.server.command.impl;

import by.epam.library.server.command.ServerCommand;
import by.epam.library.server.service.FileBaseService;
import by.epam.library.server.service.ServiceProvider;

public class GetAllFilesCommand implements ServerCommand {
    private final FileBaseService fileBaseService = ServiceProvider.getInstance().getFileBaseService();

    @Override
    public String execute(String request) {
        return fileBaseService.getAllFiles();
    }
}
