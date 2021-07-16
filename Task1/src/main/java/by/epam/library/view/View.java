package by.epam.library.view;

import by.epam.library.bean.Book;

public interface View {
    void showBooks();
    void showBooksByPages(int defaultPageNumber);
    void print(String str);
    void print(Book book);
}
