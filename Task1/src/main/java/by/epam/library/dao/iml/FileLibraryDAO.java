package by.epam.library.dao.iml;

import by.epam.library.bean.Book;
import by.epam.library.bean.BookType;
import by.epam.library.dao.LibraryDAO;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileLibraryDAO implements LibraryDAO {
    private static final File BOOKS_BASE_PATH = new File("Task1/src/main/resources/booksbase.txt");
    private static FileLibraryDAO instance;

    private FileLibraryDAO() {

    }

    public static FileLibraryDAO getInstance() {
        if (instance == null) {
            instance = new FileLibraryDAO();
        }
        return instance;
    }

    @Override
    public void writeOneBookToFile(Book book) {
        try (FileWriter writer = new FileWriter(BOOKS_BASE_PATH, true)) {
            writer.append("---\n");
            writer.write(book.toString());
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void writeBooksToFile(ArrayList<Book> books) {
        try (FileWriter writer = new FileWriter(BOOKS_BASE_PATH)) {

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

    @Override
    public ArrayList<Book> readBooksFromFile() {
        ArrayList<Book> books = new ArrayList<>();
        StringBuilder booksListFromFile = new StringBuilder();
        Pattern patternForParsingBooksFields = Pattern
                .compile("(?:№\\s*|Name:\\s*|Author:\\s*|Year:\\s|Type:\\s|Description:\\s)(.*)(?:\\n|$|)");

        try (FileReader reader = new FileReader(BOOKS_BASE_PATH)) {
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
}
