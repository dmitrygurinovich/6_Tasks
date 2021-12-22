package by.epam.archive.server.service.impl;

import by.epam.archive.server.bean.File;
import by.epam.archive.server.dao.DAOProvider;
import by.epam.archive.server.dao.FilesBaseDAO;
import by.epam.archive.server.service.FileBaseService;

public class FileBaseServiceImpl implements FileBaseService {

    public FileBaseServiceImpl() {}

    @Override
    public void addFile(File file) {
        FilesBaseDAO filesBaseDAO;

        filesBaseDAO = DAOProvider.getInstance().getFilesBaseDAO();

        filesBaseDAO.getFiles().add(file);
        filesBaseDAO.writeFilesToXmlFile();
    }

    @Override
    public void deleteFile(int fileId) {
        FilesBaseDAO filesBaseDAO;

        filesBaseDAO = DAOProvider.getInstance().getFilesBaseDAO();

        filesBaseDAO.getFiles().remove(fileId - 1);
        for (int i = 0; i < filesBaseDAO.getFiles().size(); i++) {
            filesBaseDAO.getFiles().get(i).setId(i + 1);
        }
        filesBaseDAO.writeFilesToXmlFile();
    }

    @Override
    public String getAllFiles() {
        FilesBaseDAO filesBaseDAO = DAOProvider.getInstance().getFilesBaseDAO();
        return filesBaseDAO.getXmlDocument(filesBaseDAO.getFiles())
                .replaceAll("\n", "")
                .replaceAll("\t", "");
    }
}

