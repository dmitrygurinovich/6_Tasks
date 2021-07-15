package by.epam.library.service;

import by.epam.library.bean.Book;

public interface LibraryService {
    void searchBooksByKeyword();
    void addBook();
    void editBook();
    void showBookEditingMenu(Book book);
}
