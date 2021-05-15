package by.epam.task.main;

import by.epam.task.entity.Book;
import by.epam.task.entity.BookType;
import by.epam.task.entity.Library;
import by.epam.task.logic.LibraryLogic;

/*
Задание 1: создать консольное приложение “Учет книг в домашней библиотеке”.
Общие требования к заданию:
• Система учитывает книги как в электронном, так и в бумажном варианте.
• Существующие роли: пользователь, администратор.
• Пользователь может просматривать книги в каталоге книг, осуществлять поиск книг в каталоге.
• Администратор может модифицировать каталог.
• * При добавлении описания книги в каталог оповещение о ней рассылается на e-mail всем пользователям
• ** При просмотре каталога желательно реализовать постраничный просмотр
• * Пользователь может предложить добавить книгу в библиотеку, переслав её администратору на e-mail.
• Каталог книг хранится в текстовом файле.
• Данные аутентификации пользователей хранятся в текстовом файле. Пароль не хранится в открытом виде
 */
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        LibraryLogic logic = new LibraryLogic();

        logic.addBook(library, new Book("Name 2", "Author 2", 1998, BookType.PAPER_BOOK));
        //logic.addBook(library, new Book("Name", "Author", 1987, BookType.PAPER_BOOK));

        for (Book book : library.getBooks()) {
            System.out.println(book);
        }


    }
}
