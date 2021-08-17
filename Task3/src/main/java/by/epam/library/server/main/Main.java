package by.epam.library.server.main;

import by.epam.library.server.bean.File;
import by.epam.library.server.dao.DAOProvider;
import by.epam.library.server.dao.FilesBaseDAO;

public class Main {
    public static void main(String[] args) {

        FilesBaseDAO filesBaseDAO = DAOProvider.getInstance().getFilesBaseDAO();

        for (File file : filesBaseDAO.getFiles()) {
            System.out.println(file);
        }

    }
}
