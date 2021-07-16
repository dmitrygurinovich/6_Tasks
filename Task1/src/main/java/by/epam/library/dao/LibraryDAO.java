package by.epam.library.dao;

import by.epam.library.bean.Book;

import java.util.ArrayList;

public interface LibraryDAO {

    void writeOneBookToFile(Book book);
    void writeBooksToFile(ArrayList<Book> books);
    ArrayList<Book> readBooksFromFile();

}
