package by.epam.library.server.command.impl;

import by.epam.library.server.command.Command;
import by.epam.library.server.service.FileBaseService;
import by.epam.library.server.service.ServiceProvider;

public class DeleteFileCommand implements Command {
    FileBaseService fileBaseService = ServiceProvider.getInstance().getFileBaseService();

    @Override
    public String execute(String request) {
        String response;

        response = "file deleted!";

        try {
            String[] params = request.split("\\s+");
            fileBaseService.deleteFile(Integer.parseInt(params[2]));
        } catch (NumberFormatException exception) {
            exception.printStackTrace();
        }

        return response;
    }
}
