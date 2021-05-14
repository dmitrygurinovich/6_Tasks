package by.epam.task.logic;

import by.epam.task.entity.Book;
import by.epam.task.entity.BookType;
import by.epam.task.entity.Library;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryLogic {
    private final File booksbase = new File("Task1/src/main/resources/booksbase.txt");

    public LibraryLogic() {
    }

    public void writeBooksToFile(Library library) {

        try (FileWriter writer = new FileWriter(booksbase)) {

            for (Book book : library.getBooks()) {
                writer.write(book.toString());
                writer.append("---");
            }
            writer.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public ArrayList<Book> readBooksFromFile() {
        ArrayList<Book> booksBase;
        StringBuilder booksList;
        String[] books;
        Pattern pattern;
        Matcher matcher;
        Scanner scanner;

        booksBase = new ArrayList<>();
        booksList = new StringBuilder();
        pattern = Pattern.compile("(?:№\\s*|Name:\\s*|Author:\\s*|Year:\\s|Type:\\s|Description:\\s)(.*)(?:\\n|$|)");

        try (FileReader reader = new FileReader(booksbase)) {
            scanner = new Scanner(reader);

            while (scanner.hasNextLine()) {
                booksList.append(scanner.nextLine());
                booksList.append("\n");
            }

            booksList.deleteCharAt(booksList.length() - 1);

            books = booksList.toString().split("---");

            for (int i = 0; i < books.length; i++) {
                ArrayList<String> fieldsList;

                matcher = pattern.matcher(books[i]);

                fieldsList = new ArrayList<>();

                while (matcher.find()) {
                    fieldsList.add(matcher.group(1));
                }

                booksBase.add(new Book(
                        fieldsList.get(1),
                        fieldsList.get(2),
                        Integer.parseInt(fieldsList.get(3)),
                        (fieldsList.get(4).equals("Paper book") ? BookType.PAPER_BOOK : BookType.ELECTRONIC_BOOK)
                ));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return booksBase;
    }
}
