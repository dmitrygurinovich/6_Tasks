package by.epam.archive.server.service.impl;

import by.epam.archive.server.dao.DAOProvider;
import by.epam.archive.server.dao.FilesBaseDAO;
import by.epam.archive.server.service.FileBaseService;
import by.epam.archive.server.service.ServiceProvider;

public class FileBaseServiceImpl implements FileBaseService {

    @Override
    public String addFile(String request) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        FileBaseService fileBaseService = serviceProvider.getFileBaseService();
        DAOProvider daoProvider = DAOProvider.getInstance();
        FilesBaseDAO filesBaseDAO = daoProvider.getFilesBaseDAO();

        String[]  params = request.split("&");
        StringBuilder response = new StringBuilder(params[0].concat("&").concat(params[1]).concat("&"));

        filesBaseDAO.setFiles(filesBaseDAO.parseXmlToTheListOfFiles(params[2]));
        filesBaseDAO.writeFilesToXmlFile();

        return response.append(fileBaseService.filesXmlToString()).toString();
    }

    @Override
    public String editFile(String request) {
        String[] params = request.split("&");
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        FileBaseService fileBaseService = serviceProvider.getFileBaseService();
        DAOProvider daoProvider = DAOProvider.getInstance();

        FilesBaseDAO filesBaseDAO = daoProvider.getFilesBaseDAO();
        StringBuilder response = new StringBuilder(params[1].concat("&").concat(params[1].concat("&")));

        filesBaseDAO.setFiles(filesBaseDAO.parseXmlToTheListOfFiles(params[2]));
        filesBaseDAO.writeFilesToXmlFile();

        return response.append(fileBaseService.filesXmlToString()).toString();
    }

    @Override
    public String filesXmlToString() {
        FilesBaseDAO filesBaseDAO = DAOProvider.getInstance().getFilesBaseDAO();
        return filesBaseDAO.getXmlDocument(filesBaseDAO.getFiles())
                .replaceAll("\n", "")
                .replaceAll("\t", "");
    }

    @Override
    public String deleteFile(String request) {
        String[] params = request.split("&");
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        FileBaseService fileBaseService = serviceProvider.getFileBaseService();
        DAOProvider daoProvider = DAOProvider.getInstance();

        FilesBaseDAO filesBaseDAO = daoProvider.getFilesBaseDAO();
        StringBuilder response = new StringBuilder(params[0].concat("&").concat(params[1].concat("&")));

        filesBaseDAO.setFiles(filesBaseDAO.parseXmlToTheListOfFiles(params[2]));
        filesBaseDAO.writeFilesToXmlFile();

        return response.append(fileBaseService.filesXmlToString()).toString();
    }

    @Override
    public String getAllFiles(String request) {
        String[] params = request.split("&");
        DAOProvider daoProvider = DAOProvider.getInstance();
        FilesBaseDAO filesBaseDAO = daoProvider.getFilesBaseDAO();
        StringBuilder response = new StringBuilder(params[0].concat("&").concat(params[1]).concat("&"));

        if (!filesBaseDAO.getFiles().isEmpty()) {
            return response.append(filesXmlToString()).toString();
        } else {
            return "error&no_files";
        }
    }
}

