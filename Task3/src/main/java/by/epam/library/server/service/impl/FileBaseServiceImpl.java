package by.epam.library.server.service.impl;

import by.epam.library.server.bean.File;
import by.epam.library.server.dao.DAOProvider;
import by.epam.library.server.dao.FilesBaseDAO;
import by.epam.library.server.service.FileBaseService;

import java.io.IOException;


public class FileBaseServiceImpl implements FileBaseService {

    @Override
    public void addFile(File file) {
        FilesBaseDAO filesBaseDAO;

        filesBaseDAO = DAOProvider.getInstance().getFilesBaseDAO();

        try {
            filesBaseDAO.getFiles().add(file);
            filesBaseDAO.writeFilesToXml();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void deleteFile(int fileId) {
        FilesBaseDAO filesBaseDAO;

        filesBaseDAO = DAOProvider.getInstance().getFilesBaseDAO();

        filesBaseDAO.getFiles().remove(fileId - 1);
        for (int i = 0; i < filesBaseDAO.getFiles().size(); i++) {
            filesBaseDAO.getFiles().get(i).setId(i + 1);
        }
        try {
            filesBaseDAO.writeFilesToXml();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

