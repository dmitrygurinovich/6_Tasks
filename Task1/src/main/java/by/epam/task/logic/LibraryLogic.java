package by.epam.task.logic;

import by.epam.task.entity.Book;
import by.epam.task.entity.Library;

import java.io.*;
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

    public void readBooksFromFile() {
        StringBuilder booksListFromFileToString = new StringBuilder();
        Pattern pattern = Pattern.compile("(№\\s*|Name:\\s*|Author:\\s*|Year:\\s|Type:\\s|Description:\\s)(.*)(\\n|$)");

        String[] booksToString;

        try(FileReader reader = new FileReader(booksbase)) {
            Scanner scanner = new Scanner(reader);

            while (scanner.hasNextLine()) {
                booksListFromFileToString.append(scanner.nextLine());
                booksListFromFileToString.append("\n");
            }

            booksToString = booksListFromFileToString.toString().split("---");

            String[] lines = booksToString[0].split("(№\\s*|Name:\\s*|Author:\\s*|Year:\\s|Type:\\s|Description:\\s)(.*)(\\n|$|)");
            System.out.println(lines.length);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
