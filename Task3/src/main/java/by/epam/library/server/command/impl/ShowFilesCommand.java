package by.epam.library.server.command.impl;

import by.epam.library.server.command.Command;
import by.epam.library.server.service.FileBaseService;
import by.epam.library.server.service.ServiceProvider;

public class ShowFilesCommand implements Command {
    private final FileBaseService fileBaseService = ServiceProvider.getInstance().getFileBaseService();

    @Override
    public String execute(String request) {
        String response;
        response = "All files received to the client!";

        fileBaseService.showFiles();
        return response;
    }
}
