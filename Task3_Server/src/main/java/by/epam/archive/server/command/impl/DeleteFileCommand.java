package by.epam.archive.server.command.impl;

import by.epam.archive.server.service.FileBaseService;
import by.epam.archive.server.service.ServiceProvider;
import by.epam.archive.server.command.ServerCommand;
import by.epam.archive.server.dao.DAOProvider;
import by.epam.archive.server.dao.FilesBaseDAO;

public class DeleteFileCommand implements ServerCommand {
    @Override
    public String execute(String request) {
        String[] params;
        FileBaseService fileBaseService;
        FilesBaseDAO filesBaseDAO;

        params = request.split("&");
        fileBaseService = ServiceProvider.getInstance().getFileBaseService();
        filesBaseDAO = DAOProvider.getInstance().getFilesBaseDAO();

        filesBaseDAO.setFiles(filesBaseDAO.parseXmlToTheListOfFiles(params[2]));
        filesBaseDAO.writeFilesToXmlFile();

        return params[1].concat("&").concat(fileBaseService.getAllFiles());
    }
}
