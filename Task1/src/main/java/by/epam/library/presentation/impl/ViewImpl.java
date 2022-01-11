package by.epam.library.presentation.impl;

import by.epam.library.bean.Book;
import by.epam.library.bean.Library;
import by.epam.library.bean.UserRole;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.presentation.UserInterface;
import by.epam.library.presentation.View;

import java.util.Scanner;

public final class ViewImpl implements View {

    @Override
    public void showBooks() {
        Library library = Library.getInstance();
        int defaultPageNumber = 1;

        if (library.getBooks().size() == 0) {
            print("There aren't books!");
        } else if (library.getBooks().size() <= 10) {
            print("+++ BOOKS +++\n +++ Page 1 +++");
            for (Book book : library.getBooks()) {
                print(book);
            }
        } else {
            showBooksByPages(defaultPageNumber);
        }
    }

    @Override
    public void showBooksByPages(int defaultPageNumber) {
        Scanner in = new Scanner(System.in);
        Library library = Library.getInstance();
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        UserInterface userInterface = presentationProvider.getUserInterface();
        int pagesCount = (library.getBooks().size() - (library.getBooks().size() % 10)) / 10;
        int pageNumber = defaultPageNumber;

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

    @Override
    public void print(String str) {
        System.out.println(str);
    }

    @Override
    public void print(Book book) {
        System.out.println(book);
    }
}
