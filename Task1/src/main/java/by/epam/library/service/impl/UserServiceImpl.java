package by.epam.library.service.impl;

import by.epam.library.bean.Book;
import by.epam.library.bean.BookType;
import by.epam.library.bean.User;
import by.epam.library.service.UserService;
import by.epam.library.view.impl.DataFromConsoleImpl;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;

    private UserServiceImpl() {

    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public void suggestNewBook(User user) {
        Book book;
        EmailSenderServiceIml sender;
        DataFromConsoleImpl console;

        book = new Book();
        sender = EmailSenderServiceIml.getInstance();
        console = DataFromConsoleImpl.getInstance();

        book.setName(console.getStringFromConsole("Enter book's name:"));
        book.setAuthor(console.getStringFromConsole("Enter book's author:"));
        book.setYear(console.getNumFromConsole("Enter book's year:", 1800, 2021));
        book.setType((console.getNumFromConsole("Choose book's type:\n" + "1. Paper book\n" + "2. E-book", 0, 2) == 1 ? BookType.PAPER_BOOK : BookType.ELECTRONIC_BOOK));

        sender.suggestToAddABookToTheLibrary(user, book);
    }
}
