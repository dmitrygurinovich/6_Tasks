package by.epam.library.service.impl;

import by.epam.library.bean.Book;
import by.epam.library.bean.BookType;
import by.epam.library.bean.Library;
import by.epam.library.dao.DAOProvider;
import by.epam.library.dao.LibraryDAO;
import by.epam.library.presentation.DataFromConsole;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.presentation.UserInterface;
import by.epam.library.presentation.View;
import by.epam.library.service.EmailSenderService;
import by.epam.library.service.LibraryService;
import by.epam.library.service.ServiceProvider;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LibraryServiceImpl implements LibraryService {
    private static final DAOProvider DAO_PROVIDER = DAOProvider.getInstance();
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final PresentationProvider PRESENTATION_PROVIDER = PresentationProvider.getInstance();
    private static final DataFromConsole DATA_FROM_CONSOLE = PRESENTATION_PROVIDER.getDataFromConsole();

    public LibraryServiceImpl() {

    }

    @Override
    public void searchBooksByKeyword() {
        ArrayList<Book> books = new ArrayList<>();
        View view = PRESENTATION_PROVIDER.getView();
        Library library = Library.getInstance();
        String keyword = DATA_FROM_CONSOLE.getStringFromConsole("Enter keyword for search: ");
        Pattern pattern = Pattern.compile(keyword.toLowerCase(Locale.ROOT));

        for (int i = 0; i < library.getBooks().size(); i++) {
            StringBuilder concatenateBookFields = new StringBuilder();
            concatenateBookFields
                    .append(library.getBooks().get(i).getName())
                    .append(library.getBooks().get(i).getAuthor())
                    .append(library.getBooks().get(i).getYear());

            if (library.getBooks().get(i).getDescription() != null) {
                concatenateBookFields.append(library.getBooks().get(i).getDescription());
            }

            Matcher matcher = pattern.matcher(concatenateBookFields.toString().toLowerCase(Locale.ROOT));

            if (matcher.find()) {
                books.add(library.getBooks().get(i));
            }
        }

        if (books.size() == 0) {
            view.print("No result!");
        } else {
            for (Book book : books) {
                view.print(book);
            }
        }
    }

    @Override
    public void addBook() {
        Book book;
        Library library;
        View view;
        DataFromConsole console;
        LibraryDAO libraryDAO;

        book = new Book();
        library = Library.getInstance();
        view = PRESENTATION_PROVIDER.getView();
        console = PRESENTATION_PROVIDER.getDataFromConsole();
        libraryDAO = DAOProvider.getInstance().getLibraryDAO();

        book.setName(console.getStringFromConsole("Enter book's name: "));
        book.setAuthor(console.getStringFromConsole("Enter book's author: "));
        book.setYear(console.getNumFromConsole("Enter book's year: ", 1800, 2021));
        book.setType((console.getNumFromConsole("Choose book's type:\n" + "1. Paper book\n" + "2. E-book", 1, 2) == 1 ? BookType.PAPER_BOOK : BookType.ELECTRONIC_BOOK));
        book.setId(library.getBooks().size() + 1);

        libraryDAO.writeOneBookToFile(book);
        library.getBooks().add(book);

        view.print("New book " + book.getName() + " added.");
    }

    @Override
    public void editBook() {
        int bookNumber;
        Book book;
        Library library;
        UserInterface userInterface;
        DataFromConsole console;

        library = Library.getInstance();
        userInterface = PRESENTATION_PROVIDER.getUserInterface();
        console = PRESENTATION_PROVIDER.getDataFromConsole();

        bookNumber = console.getNumFromConsole("" +
                "Enter book's number or \"0\" for exit to the main menu: ", 0, library.getBooks().size());

        if (bookNumber == 0) {
            userInterface.adminMenu();
        } else {
            book = library.getBooks().get(bookNumber - 1);
            bookEditingMenu(book);
        }

    }

    @Override
    public void bookEditingMenu(Book book) {
        int menuItem;
        String descriptionUntilEditing;
        Library library;
        View view;
        DataFromConsole dataFromConsole;
        UserInterface userInterface;
        EmailSenderService emailSenderService;
        LibraryDAO libraryService;

        library = Library.getInstance();
        view = PRESENTATION_PROVIDER.getView();
        dataFromConsole = PRESENTATION_PROVIDER.getDataFromConsole();
        userInterface = PRESENTATION_PROVIDER.getUserInterface();
        emailSenderService = SERVICE_PROVIDER.getEmailSenderService();
        libraryService = DAO_PROVIDER.getLibraryDAO();

        view.print("" +
                "You're editing book:\n" +
                "####################\n" +
                book +
                "####################");
        menuItem = dataFromConsole.getNumFromConsole("" +
                "1. Edit book's name\n" +
                "2. Edit book's author\n" +
                "3. Edit year\n" +
                "4. Edit type\n" +
                "5. Edit description\n" +
                "0. To the main menu", 0, 5);

        switch (menuItem) {
            case 0:
                libraryService.writeBooksToFile(library.getBooks());
                userInterface.adminMenu();
            case 1:
                book.setName(dataFromConsole.getStringFromConsole("Enter name: "));
                bookEditingMenu(book);
            case 2:
                book.setAuthor(dataFromConsole.getStringFromConsole("Enter author: "));
                bookEditingMenu(book);
            case 3:
                book.setYear(dataFromConsole.getNumFromConsole("Enter year", 1800, 2021));
                bookEditingMenu(book);
            case 4:
                book.setType((dataFromConsole.getNumFromConsole("1. Paper book\n2. Electronic book", 1, 2) == 1) ? BookType.PAPER_BOOK : BookType.ELECTRONIC_BOOK);
                bookEditingMenu(book);
            case 5:
                descriptionUntilEditing = book.getDescription();

                book.setDescription(dataFromConsole.getStringFromConsole("Enter description: "));
                if (!book.getDescription().equals("") && !book.getDescription().equals(descriptionUntilEditing)) {
                    emailSenderService.notifyUsersAboutAddingBookDescription("Description has been added for book!", book);
                }
                bookEditingMenu(book);
        }
    }
}
