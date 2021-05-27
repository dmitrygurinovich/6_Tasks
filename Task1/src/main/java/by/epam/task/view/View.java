package by.epam.task.view;

import by.epam.task.entity.Book;
import by.epam.task.entity.Library;

import java.util.Scanner;

public class View {
    private final Scanner in;

    public View() {
        this.in = new Scanner(System.in);
    }

    public void showBooks(Library library) {
        int defaultPageNumber = 1;
        if (library.getBooks().size() == 0) {
            System.err.println("There aren't books!");
        } else if (library.getBooks().size() > 0 && library.getBooks().size() <= 10) {
            System.out.println("+++ BOOKS +++\n +++ Page 1 +++");
            for (Book book : library.getBooks()) {
                System.out.println(book);
            }
        } else {
            showBooksByPages(library, defaultPageNumber);
        }
    }

    public void showBooksByPages(Library library, int defaultPageNumber) {
        int pagesCount;
        int pageNumber = defaultPageNumber;

        pagesCount = (library.getBooks().size() - (library.getBooks().size() % 10)) / 10;
        if (library.getBooks().size() % 10 != 0) {
            pagesCount++;
        }

        if (pageNumber < pagesCount) {
            System.out.println("---- Page " + pageNumber + " of " + pagesCount +" ----" );
            for (int i = 10 * (pageNumber - 1); i < 10 * pageNumber; i++) {
                System.out.println(library.getBooks().get(i));
            }
        } else if (pageNumber == pagesCount) {
            for (int i = 10 * (pageNumber - 1); i < library.getBooks().size(); i++) {
                System.out.println(library.getBooks().get(i));
            }
        }
        System.out.println("There are " + pagesCount + " pages!");
        System.out.println("Enter page's number (1 - " + pagesCount + "):" +
                "\nEnter 0 for exit");
        while (!in.hasNextInt()) {
            in.next();
            System.out.println("Enter page's number:");
        }
        pageNumber = in.nextInt();

        if (pageNumber == 0) {
            // выход из просмотра каталога
            // переход в меню, в зависимости от роли пользователя
            System.out.println("выход из меню");
        } else {
            showBooksByPages(library, pageNumber);
        }

    }

    public void print(String str) {
        System.out.println(str);
    }
}
