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
    private final Scanner in;
    private final View view;

    public LibraryLogic() {
        this.booksBasePath = new File("Task1/src/main/resources/booksbase.txt");
        this.in = new Scanner(System.in);
        this.view = new View();
    }

    public void addBook(Library library) {
        Book newBook;

        newBook = new Book();

        newBook.setName(getStringFromConsole("Enter book's name: "));
        newBook.setAuthor(getStringFromConsole("Enter book's author: "));
        newBook.setYear(getNumFromConsole("Enter book's year: ", 1800, 2021));
        newBook.setType((getNumFromConsole("Choose book's type:\n" + "1. Paper book\n" + "2. E-book", 0, 2) == 1 ? BookType.PAPER_BOOK : BookType.ELECTRONIC_BOOK));
        newBook.setId(library.getBooks().size() + 1);

        this.writeOneBookToFile(newBook);
        library.getBooks().add(newBook);

        view.print("New book " + newBook.getName() + " added.");
    }

    public void editBook(Library library) {
        int bookNumber;
        UserInterface userInterface;
        Book bookForEdit;

        bookForEdit = new Book();

        bookNumber = getNumFromConsole("" +
                "Enter book's number which you want to edit (\"0\" for exit to the main menu): ", -1, library.getBooks().size());

        for (Book book : library.getBooks()) {
            if (book.getId() == bookNumber) {
                bookForEdit = book;
                break;
            }
        }

        System.out.println(bookForEdit);
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

        if (number > min && number <= max) {
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
