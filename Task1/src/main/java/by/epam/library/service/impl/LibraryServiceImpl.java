package by.epam.library.service.impl;

import by.epam.library.bean.Book;
import by.epam.library.bean.BookType;
import by.epam.library.bean.Library;
import by.epam.library.dao.iml.FileLibraryDAO;
import by.epam.library.service.LibraryService;
import by.epam.library.view.View;
import by.epam.library.view.impl.DataFromConsoleImpl;
import by.epam.library.view.impl.UserInterfaceImpl;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LibraryServiceImpl implements LibraryService {
    private static LibraryServiceImpl instance;

    private LibraryServiceImpl() {

    }

    public static LibraryServiceImpl getInstance() {
        if (instance == null) {
            instance = new LibraryServiceImpl();
        }
        return instance;
    }

    @Override
    public void searchBooksByKeyword() {
        String keyword;
        StringBuilder concatenateBookFields;
        Pattern pattern;
        Matcher matcher;
        ArrayList<Book> books;
        View view = new View();
        Library library;
        DataFromConsoleImpl console;

        console = DataFromConsoleImpl.getInstance();
        keyword = console.getStringFromConsole("Enter keyword for search: ");
        pattern = Pattern.compile(keyword.toLowerCase(Locale.ROOT));
        books = new ArrayList<>();
        library = Library.getInstance();

        for (int i = 0; i < library.getBooks().size(); i++) {
            concatenateBookFields = new StringBuilder();
            concatenateBookFields
                    .append(library.getBooks().get(i).getName())
                    .append(library.getBooks().get(i).getAuthor())
                    .append(library.getBooks().get(i).getYear());

            if (library.getBooks().get(i).getDescription() != null) {
                concatenateBookFields.append(library.getBooks().get(i).getDescription());
            }

            matcher = pattern.matcher(concatenateBookFields.toString().toLowerCase(Locale.ROOT));

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
        View view;
        Library library;
        DataFromConsoleImpl console;
        FileLibraryDAO fileLibraryDAO;

        book = new Book();
        view = new View();
        library = Library.getInstance();
        console = DataFromConsoleImpl.getInstance();
        fileLibraryDAO = FileLibraryDAO.getInstance();

        book.setName(console.getStringFromConsole("Enter book's name: "));
        book.setAuthor(console.getStringFromConsole("Enter book's author: "));
        book.setYear(console.getNumFromConsole("Enter book's year: ", 1800, 2021));
        book.setType((console.getNumFromConsole("Choose book's type:\n" + "1. Paper book\n" + "2. E-book", 1, 2) == 1 ? BookType.PAPER_BOOK : BookType.ELECTRONIC_BOOK));
        book.setId(library.getBooks().size() + 1);

        fileLibraryDAO.writeOneBookToFile(book);
        library.getBooks().add(book);

        view.print("New book " + book.getName() + " added.");
    }

    @Override
    public void editBook() {
        int bookNumber;
        Book book;
        Library library;
        UserInterfaceImpl userInterface;
        DataFromConsoleImpl console;

        library = Library.getInstance();
        userInterface = UserInterfaceImpl.getInstance();
        console = DataFromConsoleImpl.getInstance();

        bookNumber = console.getNumFromConsole("" +
                "Enter book's number or \"0\" for exit to the main menu: ", 0, library.getBooks().size());

        if (bookNumber == 0) {
            userInterface.adminMenu();
        } else {
            book = library.getBooks().get(bookNumber - 1);
            showBookEditingMenu(book);
        }

    }

    @Override
    public void showBookEditingMenu(Book book) {
        int menuItem;
        String descriptionUntilEditing;
        EmailSenderServiceIml emailSenderService;
        View view;
        Library library;
        DataFromConsoleImpl console;
        UserInterfaceImpl userInterface;
        FileLibraryDAO fileLibraryDAO;

        emailSenderService = EmailSenderServiceIml.getInstance();
        view = new View();
        library = Library.getInstance();
        console = DataFromConsoleImpl.getInstance();
        userInterface = UserInterfaceImpl.getInstance();
        fileLibraryDAO = FileLibraryDAO.getInstance();

        view.print("" +
                "You're editing book:\n" +
                "####################\n" +
                book +
                "####################");
        menuItem = console.getNumFromConsole("" +
                "1. Edit book's name\n" +
                "2. Edit book's author\n" +
                "3. Edit year\n" +
                "4. Edit type\n" +
                "5. Edit description\n" +
                "0. To the main menu", 0, 5);

        switch (menuItem) {
            case 0:
                fileLibraryDAO.writeBooksToFile(library.getBooks());
                userInterface.adminMenu();
            case 1:
                book.setName(console.getStringFromConsole("Enter name: "));
                showBookEditingMenu(book);
            case 2:
                book.setAuthor(console.getStringFromConsole("Enter author: "));
                showBookEditingMenu(book);
            case 3:
                book.setYear(console.getNumFromConsole("Enter year", 1800, 2021));
                showBookEditingMenu(book);
            case 4:
                book.setType((console.getNumFromConsole("1. Paper book\n2. Electronic book", 1, 2) == 1) ? BookType.PAPER_BOOK : BookType.ELECTRONIC_BOOK);
                showBookEditingMenu(book);
            case 5:
                descriptionUntilEditing = book.getDescription();

                book.setDescription(console.getStringFromConsole("Enter description: "));
                if (!book.getDescription().equals("") && !book.getDescription().equals(descriptionUntilEditing)) {
                    emailSenderService.notifyUsersAboutAddingBooksDescription("Description has been added for book!", book);
                }
                showBookEditingMenu(book);
        }
    }
}
