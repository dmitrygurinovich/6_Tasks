package by.epam.library.server.command.impl;

import by.epam.library.server.command.ServerCommand;
import by.epam.library.server.dao.DAOProvider;
import by.epam.library.server.dao.FilesBaseDAO;
import by.epam.library.server.service.FileBaseService;
import by.epam.library.server.service.ServiceProvider;

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
