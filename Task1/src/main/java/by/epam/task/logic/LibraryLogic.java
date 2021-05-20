package by.epam.task.logic;

import by.epam.task.entity.Book;
import by.epam.task.entity.BookType;
import by.epam.task.entity.Library;
import by.epam.task.view.View;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryLogic {
    private final File booksBasePath = new File("Task1/src/main/resources/booksbase.txt");
    private final SecretKeySpec key = new SecretKeySpec("Hdy4rl1dh64MwPfn".getBytes(), "AES");
    private final Scanner in = new Scanner(System.in);
    private final View view = new View();

    public LibraryLogic() {}

    public void addBook(Library library) {
        Book newBook = new Book();

        newBook.setName(getStringFromConsole("Enter book's name: "));
        newBook.setAuthor(getStringFromConsole("Enter book's author: "));
        newBook.setYear(getNumFromConsole("Enter book's year: ", 1800, 2021));

        switch (getNumFromConsole(
                   "Choose book's type:\n" +
                           "1. Paper book\n" +
                           "2. E-book", 0, 2)) {
            case (1):
                newBook.setType(BookType.PAPER_BOOK);
                break;
            case(2):
                newBook.setType(BookType.ELECTRONIC_BOOK);
                break;
        }

        newBook.setId(library.getBooks().size() + 1);

        this.writeOneBookToFile(newBook);
        library.getBooks().add(newBook);
        view.print("New book " + newBook.getName() + " added.");
    }

    public void writeBooksToFile(ArrayList<Book> books) {
        //TODO сделать так, чтобы после последней книги не дозаписывалось "---"
        try (FileWriter writer = new FileWriter(booksBasePath)) {

            for (Book book : books) {
                writer.write(book.toString());
                writer.append("---");
            }
            writer.flush();

        } catch (IOException ex) {
            ex.printStackTrace();
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

                if (booksFieldsList.size() == 5) { // if Description is empty (== null)
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

    public byte[] encryptUserPassword(String password) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(password.getBytes());
    }

    public String decryptUserPassword(byte[] bytes) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] chars = cipher.doFinal(bytes);

        StringBuilder password = new StringBuilder("");
        for (byte b : chars) {
            password.append((char) b);
        }

        return password.toString();
    }

    public int getNumFromConsole(String message, int min, int max) {
        int number;
        view.print(message);
        while(!in.hasNextInt()) {
            view.print(message);
            in.next();
        }
        number = in.nextInt();

        if (number > min && number <= max) {
            return number;
        } else {
            return getNumFromConsole(message, min, max);
        }
    }

    public  String getStringFromConsole(String message) {
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
