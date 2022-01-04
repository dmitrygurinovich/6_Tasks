package by.epam.library.service;

import by.epam.library.bean.Book;
import by.epam.library.bean.User;

public interface EmailSenderService {

    void notifyUsersAboutAddingBookDescription(String subject, Book book);
    void suggestToAddABookToTheLibrary(Book book);

}
