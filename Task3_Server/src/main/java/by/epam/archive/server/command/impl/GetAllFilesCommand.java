package by.epam.archive.server.command.impl;

import by.epam.archive.server.dao.DAOProvider;
import by.epam.archive.server.dao.FilesBaseDAO;
import by.epam.archive.server.service.FileBaseService;
import by.epam.archive.server.service.ServiceProvider;
import by.epam.archive.server.command.ServerCommand;

public class GetAllFilesCommand implements ServerCommand {
    private final FileBaseService fileBaseService = ServiceProvider.getInstance().getFileBaseService();
    private final FilesBaseDAO filesBaseDAO = DAOProvider.getInstance().getFilesBaseDAO();

    @Override
    public String execute(String request) {
        String[] params;
        StringBuilder response;

        params = request.split("&");
        response = new StringBuilder(params[0].concat("&").concat(params[1]).concat("&"));

        if (filesBaseDAO.getFiles().isEmpty()) {
            return "error&no_files";
        } else {
            return response.append(fileBaseService.getAllFiles()).toString();
        }
    }
}
