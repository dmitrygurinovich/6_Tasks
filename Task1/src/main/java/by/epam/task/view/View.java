package by.epam.task.view;

import by.epam.task.entity.Book;
import by.epam.task.entity.Library;
import by.epam.task.entity.UserRole;

import java.util.Scanner;

public class View {

    public View() {

    }

    public void showBooks() {
        int defaultPageNumber;
        Library library;

        defaultPageNumber = 1;
        library = Library.getInstance();

        if (library.getBooks().size() == 0) {
            print("There aren't books!");
        } else if (library.getBooks().size() > 0 && library.getBooks().size() <= 10) {
            print("+++ BOOKS +++\n +++ Page 1 +++");
            for (Book book : library.getBooks()) {
                print(book);
            }
        } else {
            showBooksByPages(defaultPageNumber);
        }
    }

    public void showBooksByPages(int defaultPageNumber) {
        int pagesCount;
        Scanner in;
        Library library;
        UserInterface userInterface;

        int pageNumber = defaultPageNumber;
        in = new Scanner(System.in);
        library = Library.getInstance();
        userInterface = new UserInterface();

        pagesCount = (library.getBooks().size() - (library.getBooks().size() % 10)) / 10;
        if (library.getBooks().size() % 10 != 0) {
            pagesCount++;
        }

        if (pageNumber < pagesCount) {
            print("---- Page " + pageNumber + " of " + pagesCount + " ----");
            for (int i = 10 * (pageNumber - 1); i < 10 * pageNumber; i++) {
                print(library.getBooks().get(i));
            }
        } else if (pageNumber == pagesCount) {
            for (int i = 10 * (pageNumber - 1); i < library.getBooks().size(); i++) {
                print(library.getBooks().get(i));
            }
        }
        print("There are " + pagesCount + " pages!");
        print("Enter page's number (1 - " + pagesCount + "):" +
                "\nEnter \"0\" for enter to the main menu.");

        while (!in.hasNextInt()) {
            in.next();
            print("Enter page's number:");
        }
        pageNumber = in.nextInt();

        if (pageNumber == 0) {
            if (library.getAuthorizedUser().getRole().equals(UserRole.USER)) {
                userInterface.userMenu();
            } else {
                userInterface.adminMenu();
            }
        } else {
            showBooksByPages(pageNumber);
        }
    }

    public void print(String str) {
        System.out.println(str);
    }

    public void print(Book book) {
        System.out.println(book);
    }
}
