package by.epam.library.dao;

import by.epam.library.dao.iml.FileLibraryDAO;
import by.epam.library.dao.iml.FileUserBaseDAO;

public final class DAOProvider {
    private static DAOProvider instance;
    private final UserBaseDAO USER_BASE_DAO = new FileUserBaseDAO();
    private final LibraryDAO LIBRARY_DAO = new FileLibraryDAO();

    private DAOProvider() {

    }

    public static DAOProvider getInstance() {
        if (instance == null) {
            instance = new DAOProvider();
        }
        return instance;
    }

    public UserBaseDAO getUserBaseDAO() {
        return USER_BASE_DAO;
    }

    public LibraryDAO getLibraryDAO() {
        return LIBRARY_DAO;
    }
}
