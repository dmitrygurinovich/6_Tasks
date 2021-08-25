package by.epam.library.server.command.impl;

import by.epam.library.server.command.Command;
import by.epam.library.server.service.FileBaseService;
import by.epam.library.server.service.ServiceProvider;

public class ShowFilesCommand implements Command {
    private final FileBaseService fileBaseService = ServiceProvider.getInstance().getFileBaseService();

    @Override
    public void execute(String request) {
        fileBaseService.showFiles();
    }
}
