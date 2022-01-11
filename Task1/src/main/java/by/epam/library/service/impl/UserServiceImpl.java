package by.epam.library.service.impl;

import by.epam.library.bean.Book;
import by.epam.library.bean.BookType;
import by.epam.library.bean.User;
import by.epam.library.presentation.DataFromConsole;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.service.EmailSenderService;
import by.epam.library.service.ServiceProvider;
import by.epam.library.service.UserService;

public final class UserServiceImpl implements UserService {

    @Override
    public void suggestNewBook(User user) {
        Book book = new Book();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        PresentationProvider viewProvider = PresentationProvider.getInstance();
        DataFromConsole dataFromConsole = viewProvider.getDataFromConsole();
        EmailSenderService emailSenderService = serviceProvider.getEmailSenderService();

        book.setName(dataFromConsole.getStringFromConsole("Enter book's name:"));
        book.setAuthor(dataFromConsole.getStringFromConsole("Enter book's author:"));
        book.setYear(dataFromConsole.getNumFromConsole("Enter book's year:", 1800, 2021));
        book.setType((dataFromConsole.getNumFromConsole("Choose book's type:\n" + "1. Paper book\n" + "2. E-book", 0, 2) == 1 ? BookType.PAPER_BOOK : BookType.ELECTRONIC_BOOK));

        emailSenderService.suggestToAddABookToTheLibrary(book);
    }
}
