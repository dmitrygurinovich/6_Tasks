package by.epam.library.server.main;

import by.epam.library.server.dao.DAOProvider;
import by.epam.library.server.dao.FilesBaseDAO;
import by.epam.library.server.entity.File;
import by.epam.library.server.service.FileBaseService;
import by.epam.library.server.service.ServiceProvider;

public class Main {
    public static void main(String[] args) {
        //Server.getInstance().runServer();

        FileBaseService fileBaseService = ServiceProvider.getInstance().getFileBaseService();
        FilesBaseDAO filesBaseDAO = DAOProvider.getInstance().getFilesBaseDAO();

        for (File file : filesBaseDAO.getFiles()) {
            System.out.println(file);
        }

    }
}
