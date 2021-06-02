package by.epam.task.entity;

import by.epam.task.logic.LibraryLogic;
import by.epam.task.logic.UsersBaseLogic;

import java.util.ArrayList;
import java.util.Objects;

public class Library {
    private ArrayList<Book> books;
    private ArrayList<User> users;
    private static final LibraryLogic libraryLogic = new LibraryLogic();
    private static final UsersBaseLogic userBaseLogic = new UsersBaseLogic();
    private User authorizedUser;

    public Library() {
        this.books = libraryLogic.readBooksFromFile();
        this.users = userBaseLogic.readUsersFromFile();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return books.equals(library.books) &&
                users.equals(library.users) &&
                authorizedUser.equals(library.authorizedUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(books, users, authorizedUser);
    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                ", users=" + users +
                ", authorizedUser=" + authorizedUser +
                '}';
    }
}
