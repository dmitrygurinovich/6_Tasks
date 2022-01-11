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

    @Override
    public void searchBooksByKeyword() {
        ArrayList<Book> books = new ArrayList<>();
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        DataFromConsole dataFromConsole = presentationProvider.getDataFromConsole();
        View view = presentationProvider.getView();
        String keyword = dataFromConsole.getStringFromConsole("Enter keyword for search: ");
        Pattern pattern = Pattern.compile(keyword.toLowerCase(Locale.ROOT));
        Library library = Library.getInstance();

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

        if (books.size() != 0) {
            for (Book book : books) {
                view.print(book);
            }
        } else {
            view.print("No result!");
        }
    }

    @Override
    public void addBook() {
        Book book = new Book();
        DAOProvider daoProvider = DAOProvider.getInstance();
        LibraryDAO libraryDAO = daoProvider.getLibraryDAO();
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        DataFromConsole dataFromConsole = presentationProvider.getDataFromConsole();
        View view = presentationProvider.getView();
        Library library = Library.getInstance();

        book.setName(dataFromConsole.getStringFromConsole("Enter book's name: "));
        book.setAuthor(dataFromConsole.getStringFromConsole("Enter book's author: "));
        book.setYear(dataFromConsole.getNumFromConsole("Enter book's year: ", 1800, 2021));
        book.setType((dataFromConsole.getNumFromConsole("Choose book's type:\n" + "1. Paper book\n" + "2. E-book", 1, 2) == 1 ? BookType.PAPER_BOOK : BookType.ELECTRONIC_BOOK));
        book.setId(library.getBooks().size() + 1);

        libraryDAO.writeOneBookToFile(book);
        library.getBooks().add(book);

        view.print("New book " + book.getName() + " added.");
    }

    @Override
    public void editBook() {
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        DataFromConsole dataFromConsole = presentationProvider.getDataFromConsole();
        UserInterface userInterface = presentationProvider.getUserInterface();
        Library library = Library.getInstance();

        int bookNumber = dataFromConsole.getNumFromConsole("" +
                "Enter book's number or \"0\" for exit to the main menu: ", 0, library.getBooks().size());

        if (bookNumber != 0) {
            Book book = library.getBooks().get(bookNumber - 1);
            bookEditingMenu(book);
        } else {
            userInterface.adminMenu();
        }

    }

    @Override
    public void bookEditingMenu(Book book) {
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        DataFromConsole dataFromConsole = presentationProvider.getDataFromConsole();
        UserInterface userInterface = presentationProvider.getUserInterface();
        View view = presentationProvider.getView();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        EmailSenderService emailSenderService = serviceProvider.getEmailSenderService();
        DAOProvider daoProvider = DAOProvider.getInstance();
        LibraryDAO libraryDAO = daoProvider.getLibraryDAO();
        Library library = Library.getInstance();

        view.print("" +
                "You're editing book:\n" +
                "####################\n" +
                book +
                "####################");
        int menuItem = dataFromConsole.getNumFromConsole("" +
                "1. Edit book's name\n" +
                "2. Edit book's author\n" +
                "3. Edit year\n" +
                "4. Edit type\n" +
                "5. Edit description\n" +
                "0. To the main menu", 0, 5);

        switch (menuItem) {
            case 0:
                libraryDAO.writeBooksToFile(library.getBooks());
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
                String descriptionUntilEditing = book.getDescription();

                book.setDescription(dataFromConsole.getStringFromConsole("Enter description: "));
                if (!book.getDescription().equals("") && !book.getDescription().equals(descriptionUntilEditing)) {
                    emailSenderService.notifyUsersAboutAddingBookDescription("Description has been added for book!", book);
                }
                bookEditingMenu(book);
        }
    }
}
