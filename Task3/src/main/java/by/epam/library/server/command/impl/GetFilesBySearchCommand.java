package by.epam.library.server.command.impl;

import by.epam.library.server.command.Command;
import by.epam.library.server.service.FileBaseService;
import by.epam.library.server.service.ServiceProvider;

public class GetFilesBySearchCommand implements Command {
    FileBaseService fileBaseService = ServiceProvider.getInstance().getFileBaseService();

    @Override
    public String execute(String request) {
        return fileBaseService.searchFilesByKeywords(request);
    }
}
