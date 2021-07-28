package by.epam.library.service.impl;

import by.epam.library.bean.Book;
import by.epam.library.bean.BookType;
import by.epam.library.bean.User;
import by.epam.library.presentation.DataFromConsole;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.service.ServiceProvider;
import by.epam.library.service.UserService;

public final class UserServiceImpl implements UserService {
    private final static ServiceProvider serviceProvider = ServiceProvider.getInstance();
    private static final PresentationProvider viewProvider = PresentationProvider.getInstance();

    public UserServiceImpl() {}

    @Override
    public void suggestNewBook(User user) {
        Book book;
        DataFromConsole console = viewProvider.getDataFromConsole();

        book = new Book();

        book.setName(console.getStringFromConsole("Enter book's name:"));
        book.setAuthor(console.getStringFromConsole("Enter book's author:"));
        book.setYear(console.getNumFromConsole("Enter book's year:", 1800, 2021));
        book.setType((console.getNumFromConsole("Choose book's type:\n" + "1. Paper book\n" + "2. E-book", 0, 2) == 1 ? BookType.PAPER_BOOK : BookType.ELECTRONIC_BOOK));

        serviceProvider.getEmailSenderService().suggestToAddABookToTheLibrary(user, book);
    }
}
