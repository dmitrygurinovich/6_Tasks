package by.epam.task.view;

import by.epam.task.entity.Book;
import by.epam.task.entity.Library;
import by.epam.task.entity.UserRole;

import java.util.Scanner;

public class View {

    public View() {

    }

    public void showBooks(UserInterface userInterface) {
        int defaultPageNumber = 1;
        if (Library.getInstance().getBooks().size() == 0) {
            print("There aren't books!");
        } else if (Library.getInstance().getBooks().size() > 0 && Library.getInstance().getBooks().size() <= 10) {
            print("+++ BOOKS +++\n +++ Page 1 +++");
            for (Book book : Library.getInstance().getBooks()) {
                print(book);
            }
        } else {
            showBooksByPages(defaultPageNumber, userInterface);
        }
    }

    public void showBooksByPages(int defaultPageNumber, UserInterface userInterface) {
        int pagesCount;
        Scanner in;

        int pageNumber = defaultPageNumber;


        pagesCount = (Library.getInstance().getBooks().size() - (Library.getInstance().getBooks().size() % 10)) / 10;
        if (Library.getInstance().getBooks().size() % 10 != 0) {
            pagesCount++;
        }

        if (pageNumber < pagesCount) {
            print("---- Page " + pageNumber + " of " + pagesCount + " ----");
            for (int i = 10 * (pageNumber - 1); i < 10 * pageNumber; i++) {
                print(Library.getInstance().getBooks().get(i));
            }
        } else if (pageNumber == pagesCount) {
            for (int i = 10 * (pageNumber - 1); i < Library.getInstance().getBooks().size(); i++) {
                print(Library.getInstance().getBooks().get(i));
            }
        }
        print("There are " + pagesCount + " pages!");
        print("Enter page's number (1 - " + pagesCount + "):" +
                "\nEnter \"0\" for enter to the main menu.");

        in = new Scanner(System.in);
        while (!in.hasNextInt()) {
            in.next();
            print("Enter page's number:");
        }
        pageNumber = in.nextInt();

        if (pageNumber == 0) {
            if (Library.getInstance().getAuthorizedUser().getRole().equals(UserRole.USER)) {
                userInterface.userMenu();
            } else {
                userInterface.adminMenu();
            }
        } else {
            showBooksByPages(pageNumber, userInterface);
        }
    }

    public void print(String str) {
        System.out.println(str);
    }

    public void print(Book book) {
        System.out.println(book);
    }
}
