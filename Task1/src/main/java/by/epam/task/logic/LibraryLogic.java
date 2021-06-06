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

    public void searchByKeyword(Library library) {
        String keyword;
        StringBuilder[] concatenateBooksFields;
        StringBuilder concatenateBookFields;
        Pattern pattern;
        Matcher matcher;

        keyword = getStringFromConsole("Enter keyword for search: ");
        concatenateBooksFields = new StringBuilder[library.getBooks().size()];
        pattern = Pattern.compile("\\." + keyword + "\\.");

        for (int i = 0; i < library.getBooks().size(); i++) {
            concatenateBookFields = new StringBuilder();
            concatenateBookFields
                    .append(library.getBooks().get(i).getName())
                    .append(library.getBooks().get(i).getAuthor())
                    .append(library.getBooks().get(i).getYear())
                    .append(library.getBooks().get(i).getDescription())
                    .append((library.getBooks().get(i).getDescription() != null ? library.getBooks().get(i).getDescription() : ""));

            concatenateBooksFields[i] = concatenateBookFields;
        }
    }

    public void addBook(Library library) {
        Book newBook;

        newBook = new Book();

        newBook.setName(getStringFromConsole("Enter book's name: "));
        newBook.setAuthor(getStringFromConsole("Enter book's author: "));
        newBook.setYear(getNumFromConsole("Enter book's year: ", 1800, 2021));
        newBook.setType((getNumFromConsole("Choose book's type:\n" + "1. Paper book\n" + "2. E-book", 1, 2) == 1 ? BookType.PAPER_BOOK : BookType.ELECTRONIC_BOOK));
        newBook.setId(library.getBooks().size() + 1);

        this.writeOneBookToFile(newBook);
        library.getBooks().add(newBook);

        view.print("New book " + newBook.getName() + " added.");
    }

    public void editBook(Library library, UserInterface userInterface) {
        int bookNumber;

        Book book;

        bookNumber = getNumFromConsole("" +
                "Enter book's number or \"0\" for exit to the main menu: ", 0, library.getBooks().size());

        if (bookNumber == 0) {
            userInterface.adminMenu();
        } else {
            book = library.getBooks().get(bookNumber - 1);
            showBookEditingMenu(library, book, userInterface);
        }

    }

    public void showBookEditingMenu(Library library, Book book, UserInterface userInterface){
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

        switch (menuItem){
            case 0:
                writeBooksToFile(library.getBooks());
                userInterface.adminMenu();
            case 1:
                book.setName(getStringFromConsole("Enter name: "));
                showBookEditingMenu(library, book, userInterface);
            case 2:
                book.setAuthor(getStringFromConsole("Enter author: "));
                showBookEditingMenu(library, book, userInterface);
            case 3:
                book.setYear(getNumFromConsole("Enter year", 1800, 2021));
                showBookEditingMenu(library, book, userInterface);
            case 4:
                book.setType((getNumFromConsole("1. Paper book\n2. Electronic book",1,2) == 1) ? BookType.PAPER_BOOK : BookType.ELECTRONIC_BOOK);
                showBookEditingMenu(library, book, userInterface);
            case 5:
                descriptionUntilEditing = book.getDescription();

                book.setDescription(getStringFromConsole("Enter description: "));
                if (!book.getDescription().equals("") && !book.getDescription().equals(descriptionUntilEditing)) {
                    sender.notifyUsersAboutAddingBooksDescription(library, "Description has been added for book!", book);
                }
                showBookEditingMenu(library, book, userInterface);
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
