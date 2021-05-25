package by.epam.task.logic;

import by.epam.task.entity.Book;
import by.epam.task.entity.BookType;
import by.epam.task.entity.User;
import by.epam.task.view.View;

import java.util.Scanner;

public class UserLogic {
    private final Scanner in = new Scanner(System.in);
    private final View view = new View();
    private final EmailSender sender = new EmailSender();

    public UserLogic() {}

    public void suggestNewBook(User user) {
        Book book;

        book = new Book();

        book.setName(getStringFromConsole("Enter book's name:"));
        book.setAuthor(getStringFromConsole("Enter book's author:"));
        book.setYear(getNumFromConsole("Enter book's year:", 1800, 2021));
        book.setType((getNumFromConsole("Choose book's type:\n" + "1. Paper book\n" + "2. E-book", 0, 2) == 1 ? BookType.PAPER_BOOK : BookType.ELECTRONIC_BOOK));

        sender.suggestingToAddABookToTheLibrary(user, book);
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
