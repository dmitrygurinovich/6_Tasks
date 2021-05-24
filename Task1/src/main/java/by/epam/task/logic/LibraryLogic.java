package by.epam.task.logic;

import by.epam.task.entity.*;
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
    private final File usersBasePath = new File("Task1/src/main/resources/usersbase.txt");
    private final SecretKeySpec key = new SecretKeySpec("Hdy4rl1dh64MwPfn".getBytes(), "AES");
    private final Scanner in = new Scanner(System.in);
    private final View view = new View();

    public LibraryLogic() {}

    /**
     *
     * @param library - Library
     */
    public void addBook(Library library) {
        Book newBook = new Book();

        newBook.setName(getStringFromConsole("Enter book's name: "));
        newBook.setAuthor(getStringFromConsole("Enter book's author: "));
        newBook.setYear(getNumFromConsole("Enter book's year: ", 1800, 2021));
        newBook.setType((getNumFromConsole("Choose book's type:\n" + "1. Paper book\n" + "2. E-book", 0, 2) == 1 ? BookType.PAPER_BOOK : BookType.ELECTRONIC_BOOK));
        newBook.setId(library.getBooks().size() + 1);

        this.writeOneBookToFile(newBook);
        library.getBooks().add(newBook);

        view.print("New book " + newBook.getName() + " added.");
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

    /**
     * @param books - ArrayList<Book>
     */
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

    public User addUser(Library library) {
        User user = new User();

        user.setName(this.getStringFromConsole("Enter user's name: "));
        // TODO: добавить проверку на существование логина в базе
        user.setLogin(this.getStringFromConsole("Enter user's login: "));
        user.setPassword(this.getStringFromConsole("Enter password: "));
        user.setRole((getNumFromConsole("Choose user's role:\n1. Administrator\n2. User", 0,2) == 1 ? UserRole.ADMINISTRATOR : UserRole.USER));
        //TODO: проверить e-main через регулярку
        user.setEmail(getStringFromConsole("Enter user's email: "));
        view.print("User added!");

        library.getUsers().add(user);
        //this.writeUserToFile(user);
        return user;
    }

    public void writeUserToFile(User user) {

    }

    public void readUsersFromFile() {

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

        StringBuilder password = new StringBuilder();
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
        in.nextLine();
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
