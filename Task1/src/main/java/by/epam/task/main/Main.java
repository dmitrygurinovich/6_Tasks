package by.epam.task.main;

import by.epam.task.entity.*;
import by.epam.task.view.Menu;


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
			
			Book[] books = new Book[] {
					new Book("Anna Karenina", "Leo Tolstoy", 1878, BookType.PAPER_BOOK),
					new Book("To Kill a Mockingbird", "Harper Lee", 1960, BookType.PAPER_BOOK),
					new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, BookType.ELECTRONIC_BOOK),
					new Book("One Hundred Years of Solitude", "Gabriel García Márquez", 1967, BookType.PAPER_BOOK)
			};
			
			Library library = new Library("Minsk national library", books);
			
			User admin = new User("Dmitry", "gurinovich", "4531689925qWe", UserRole.ADMINISTRATOR);
			User user = new User("User", "user", "11223344", UserRole.USER);
			
			library.getUsers().add(admin);
			library.getUsers().add(user);
			
			System.out.println(library.getUsers().size());
			
			Menu menu = new Menu();
			menu.autorization(library);
	}
}
