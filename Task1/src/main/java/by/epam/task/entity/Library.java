package by.epam.task.entity;

import by.epam.task.logic.LibraryLogic;
import by.epam.task.logic.UsersBaseLogic;

import java.util.ArrayList;

public class Library {
    private ArrayList<Book> books;
    private ArrayList<User> users;
    private User authorizedUser;

    public Library() {
        this.books = new LibraryLogic().readBooksFromFile();
        this.users = new UsersBaseLogic().readUsersFromFile();
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
