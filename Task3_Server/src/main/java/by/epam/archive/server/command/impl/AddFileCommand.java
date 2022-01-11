package by.epam.archive.server.command.impl;

import by.epam.archive.server.command.ServerCommand;
import by.epam.archive.server.service.FileBaseService;
import by.epam.archive.server.service.ServiceProvider;

public class AddFileCommand implements ServerCommand {
    @Override
    public String execute(String request) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        FileBaseService fileBaseService = serviceProvider.getFileBaseService();

        return fileBaseService.addFile(request);
    }
}
