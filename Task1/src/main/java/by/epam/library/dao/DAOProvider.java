package by.epam.library.dao;

import by.epam.library.dao.iml.FileLibraryDAO;
import by.epam.library.dao.iml.FileUserBaseDAO;

public final class DAOProvider {
    private static DAOProvider instance;
    private final UserBaseDAO userBaseDAO = new FileUserBaseDAO();
    private final LibraryDAO libraryDAO = new FileLibraryDAO();

    private DAOProvider() {}

    public static DAOProvider getInstance() {
        if (instance == null) {
            instance = new DAOProvider();
        }
        return instance;
    }

    public UserBaseDAO getUserBaseDAO() {
        return userBaseDAO;
    }

    public LibraryDAO getLibraryDAO() {
        return libraryDAO;
    }
}
