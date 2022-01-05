package by.epam.library.bean;

import by.epam.library.dao.DAOProvider;
import by.epam.library.dao.LibraryDAO;
import by.epam.library.dao.UserBaseDAO;

import java.util.ArrayList;

public final class Library {
    private User authorizedUser;
    private static Library instance;

    private final DAOProvider DAO_PROVIDER = DAOProvider.getInstance();
    private final LibraryDAO LIBRARY_DAO = DAO_PROVIDER.getLibraryDAO();
    private final UserBaseDAO USER_BASE_DAO = DAO_PROVIDER.getUserBaseDAO();

    private ArrayList<Book> books = LIBRARY_DAO.readBooksFromFile();
    private ArrayList<User> users = USER_BASE_DAO.readUsersFromFile();

    private Library() {

    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public User getAuthorizedUser() {
        return authorizedUser;
    }

    public void setAuthorizedUser(User authorizedUser) {
        this.authorizedUser = authorizedUser;
    }

}
