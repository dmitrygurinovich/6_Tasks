package by.epam.archive.server.command.impl;

import by.epam.archive.server.service.FileBaseService;
import by.epam.archive.server.service.ServiceProvider;
import by.epam.archive.server.command.ServerCommand;
import by.epam.archive.server.dao.DAOProvider;
import by.epam.archive.server.dao.FilesBaseDAO;

public class AddFileCommand implements ServerCommand {
    @Override //TODO перенести логику в сервисы, а в командах только вызывать
    public String execute(String request) {
        FileBaseService fileBaseService;
        FilesBaseDAO filesBaseDAO;
        String[] params;
        StringBuilder response;

        fileBaseService = ServiceProvider.getInstance().getFileBaseService();
        filesBaseDAO = DAOProvider.getInstance().getFilesBaseDAO();
        params = request.split("&");
        response = new StringBuilder(params[0].concat("&").concat(params[1]).concat("&"));

        filesBaseDAO.setFiles(filesBaseDAO.parseXmlToTheListOfFiles(params[2]));
        filesBaseDAO.writeFilesToXmlFile();

        return response.append(fileBaseService.getAllFiles()).toString();
    }
}
