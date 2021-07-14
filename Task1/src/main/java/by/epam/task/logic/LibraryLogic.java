package by.epam.task.logic;

import by.epam.task.entity.Book;
import by.epam.task.entity.BookType;
import by.epam.task.entity.Library;
import by.epam.task.view.UserInterface;
import by.epam.task.view.View;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryLogic {
    private final File booksBasePath;
    private static final Scanner in = new Scanner(System.in);
    private static final EmailSender sender = new EmailSender();
    private final View view;

    public LibraryLogic() {
        this.booksBasePath = new File("Task1/src/main/resources/booksbase.txt");
        this.view = new View();
    }

    public void searchBooksByKeyword() {
        String keyword;
        StringBuilder concatenateBookFields;
        Pattern pattern;
        Matcher matcher;
        ArrayList<Book> books;

        keyword = getStringFromConsole("Enter keyword for search: ");
        pattern = Pattern.compile(keyword.toLowerCase(Locale.ROOT));
        books = new ArrayList<>();

        for (int i = 0; i < Library.getInstance().getBooks().size(); i++) {
            concatenateBookFields = new StringBuilder();
            concatenateBookFields
                    .append(Library.getInstance().getBooks().get(i).getName())
                    .append(Library.getInstance().getBooks().get(i).getAuthor())
                    .append(Library.getInstance().getBooks().get(i).getYear());

            if (Library.getInstance().getBooks().get(i).getDescription() != null) {
                concatenateBookFields.append(Library.getInstance().getBooks().get(i).getDescription());
            }

            matcher = pattern.matcher(concatenateBookFields.toString().toLowerCase(Locale.ROOT));

            if (matcher.find()) {
                books.add(Library.getInstance().getBooks().get(i));
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

    public void addBook() {
        Book newBook;

        newBook = new Book();

        newBook.setName(getStringFromConsole("Enter book's name: "));
        newBook.setAuthor(getStringFromConsole("Enter book's author: "));
        newBook.setYear(getNumFromConsole("Enter book's year: ", 1800, 2021));
        newBook.setType((getNumFromConsole("Choose book's type:\n" + "1. Paper book\n" + "2. E-book", 1, 2) == 1 ? BookType.PAPER_BOOK : BookType.ELECTRONIC_BOOK));
        newBook.setId(Library.getInstance().getBooks().size() + 1);

        this.writeOneBookToFile(newBook);
        Library.getInstance().getBooks().add(newBook);

        view.print("New book " + newBook.getName() + " added.");
    }

    public void editBook( UserInterface userInterface) {
        int bookNumber;

        Book book;

        bookNumber = getNumFromConsole("" +
                "Enter book's number or \"0\" for exit to the main menu: ", 0, Library.getInstance().getBooks().size());

        if (bookNumber == 0) {
            userInterface.adminMenu();
        } else {
            book = Library.getInstance().getBooks().get(bookNumber - 1);
            showBookEditingMenu(book, userInterface);
        }

    }

    public void showBookEditingMenu(Book book, UserInterface userInterface) {
        int menuItem;
        String descriptionUntilEditing;

        view.print("" +
                "You're editing book:\n" +
                "####################\n" +
                book +
                "####################");
        menuItem = getNumFromConsole("" +
                "1. Edit book's name\n" +
                "2. Edit book's author\n" +
                "3. Edit year\n" +
                "4. Edit type\n" +
                "5. Edit description\n" +
                "0. To the main menu", 0, 5);

        switch (menuItem) {
            case 0:
                writeBooksToFile(Library.getInstance().getBooks());
                userInterface.adminMenu();
            case 1:
                book.setName(getStringFromConsole("Enter name: "));
                showBookEditingMenu(book, userInterface);
            case 2:
                book.setAuthor(getStringFromConsole("Enter author: "));
                showBookEditingMenu(book, userInterface);
            case 3:
                book.setYear(getNumFromConsole("Enter year", 1800, 2021));
                showBookEditingMenu(book, userInterface);
            case 4:
                book.setType((getNumFromConsole("1. Paper book\n2. Electronic book", 1, 2) == 1) ? BookType.PAPER_BOOK : BookType.ELECTRONIC_BOOK);
                showBookEditingMenu(book, userInterface);
            case 5:
                descriptionUntilEditing = book.getDescription();

                book.setDescription(getStringFromConsole("Enter description: "));
                if (!book.getDescription().equals("") && !book.getDescription().equals(descriptionUntilEditing)) {
                    sender.notifyUsersAboutAddingBooksDescription("Description has been added for book!", book);
                }
                showBookEditingMenu(book, userInterface);
        }
    }

    public void writeOneBookToFile(Book book) {
        try (FileWriter writer = new FileWriter(booksBasePath, true)) {
            writer.append("---\n");
            writer.write(book.toString());
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeBooksToFile(ArrayList<Book> books) {
        try (FileWriter writer = new FileWriter(booksBasePath)) {

            for (int i = 0; i < books.size() - 1; i++) {
                writer.write(books.get(i).toString());
                writer.append("---\n");
            }
            writer.write(books.get(books.size() - 1).toString());
            writer.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Book> readBooksFromFile() {
        ArrayList<Book> books = new ArrayList<>();
        StringBuilder booksListFromFile = new StringBuilder();
        Pattern patternForParsingBooksFields = Pattern
                .compile("(?:№\\s*|Name:\\s*|Author:\\s*|Year:\\s|Type:\\s|Description:\\s)(.*)(?:\\n|$|)");

        try (FileReader reader = new FileReader(booksBasePath)) {
            Scanner scanner = new Scanner(reader);

            while (scanner.hasNextLine()) {
                booksListFromFile.append(scanner.nextLine());
                booksListFromFile.append("\n");
            }

            String[] booksListFromFileDividedByBook = booksListFromFile.toString().split("---\n");

            for (String book : booksListFromFileDividedByBook) {
                ArrayList<String> booksFieldsList = new ArrayList<>();
                Matcher matcher = patternForParsingBooksFields.matcher(book);

                while (matcher.find()) {
                    booksFieldsList.add(matcher.group(1));
                }

                if (booksFieldsList.size() == 5) {
                    books.add(new Book(
                            booksFieldsList.get(1),
                            booksFieldsList.get(2),
                            Integer.parseInt(booksFieldsList.get(3)),
                            (booksFieldsList.get(4).equals("Paper book") ? BookType.PAPER_BOOK : BookType.ELECTRONIC_BOOK)
                    ));
                } else {
                    books.add(new Book(
                            booksFieldsList.get(1),
                            booksFieldsList.get(2),
                            Integer.parseInt(booksFieldsList.get(3)),
                            (booksFieldsList.get(4).equals("Paper book") ? BookType.PAPER_BOOK : BookType.ELECTRONIC_BOOK),
                            booksFieldsList.get(5)
                    ));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return books;
    }

    public int getNumFromConsole(String message, int min, int max) {
        int number;
        view.print(message);
        while (!in.hasNextInt()) {
            view.print(message);
            in.next();
        }
        number = in.nextInt();
        in.nextLine();

        if (number >= min && number <= max) {
            return number;
        } else {
            return getNumFromConsole(message, min, max);
        }
    }

    public String getStringFromConsole(String message) {
        String text;
        view.print(message);

        while (!in.hasNextLine()) {
            view.print(message);
            in.next();
        }
        text = in.nextLine();
        return text;
    }

}
