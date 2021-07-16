package by.epam.library.bean;

import by.epam.library.dao.DAOProvider;

import java.util.ArrayList;

public final class Library {
    private ArrayList<Book> books;
    private ArrayList<User> users;
    private User authorizedUser;
    private static Library instance;

    private static final DAOProvider provider = DAOProvider.getInstance();

    private Library() {
        this.books = provider.getLibraryDAO().readBooksFromFile();
        this.users = provider.getUserBaseDAO().readUsersFromFile();
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
