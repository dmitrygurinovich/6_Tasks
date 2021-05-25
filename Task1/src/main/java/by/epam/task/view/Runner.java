package by.epam.task.view;

import java.util.Scanner;

import by.epam.task.entity.Library;
import by.epam.task.entity.User;
import by.epam.task.entity.UserRole;
import by.epam.task.logic.LibraryLogic;
import by.epam.task.logic.UserLogic;
import by.epam.task.logic.UsersBaseLogic;

public class Runner {
	private final Scanner in = new Scanner(System.in);
	private final View view = new View();
	private Library library = new Library();
	private final LibraryLogic libraryLogic = new LibraryLogic();
	private final UserLogic userLogic = new UserLogic();
	private  UsersBaseLogic usersBaseLogic = new UsersBaseLogic();
	private User user;


	public Runner() {}

	public void adminMenu() {
		int minMenuItem;
		int maxMenuItem;

		minMenuItem = 0;
		maxMenuItem = 5;

		view.print("" +
				"+++ ADMIN MENU +++\n" +
				"1. Show catalog\n" +
				"2. Search book\n" +
				"3. Add book\n" +
				"4. Edit book\n" +
				"5. Add user\n" +
				"0. Main menu");
		if (getMenuItem(minMenuItem, maxMenuItem) == 1){
			view.showBooksByPages(library, 1);
		} else if (getMenuItem(minMenuItem, maxMenuItem) == 2) {
			// TODO реализовать поиск
		} else if (getMenuItem(minMenuItem, maxMenuItem) == 3) {
			libraryLogic.addBook(library);
			adminMenu();
		} else if (getMenuItem(minMenuItem, maxMenuItem) == 4) {
			// TODO реализовать правку книги
		} else if (getMenuItem(minMenuItem, maxMenuItem) == 5) {
			usersBaseLogic.addUser(library);
			adminMenu();
		} else if (getMenuItem(minMenuItem, maxMenuItem) == 0) {
			exit();
		}
	}

	public void userMenu() {
		int minMenuItem;
		int maxMenuItem;

		minMenuItem = 0;
		maxMenuItem = 4;

		view.print("" +
				"+++ USER MENU +++\n" +
				"1. Show catalog\n" +
				"2. Search book\n" +
				"3. Suggest new book"+
				"0. Main menu");

		if (getMenuItem(minMenuItem, maxMenuItem) == 1) {
			view.showBooksByPages(library, 1);
			userMenu();
		}
		if (getMenuItem(minMenuItem, maxMenuItem) == 2) {
			// TODO реализовать поиск
		}
		if (getMenuItem(minMenuItem, maxMenuItem) == 3) {
			userLogic.suggestNewBook(user);
			userMenu();
		}
		if (getMenuItem(minMenuItem, maxMenuItem) == 0) {
			exit();
		}

	}

	public void authorisation() {
		String login;
		String password;
		boolean authorized = false;

		view.print("Enter login: ");
		while (!in.hasNextLine()) {
			in.next();
			view.print("Enter login: ");
		}
		login = in.nextLine();

		view.print("Enter password: ");
		while (!in.hasNextLine()) {
			in.next();
			view.print("Enter password: ");
		}
		password = in.nextLine();

		for (int i = 0; i < library.getUsers().size(); i++) {
			if (library.getUsers().get(i).getLogin().equals(login)) {
				if (library.getUsers().get(i).getPassword().equals(password)) {
					authorized = true;
					user = library.getUsers().get(i);
					if (library.getUsers().get(i).getRole().equals(UserRole.ADMINISTRATOR)) {
						adminMenu();
					} else {
						userMenu();
					}
					break;
				}
			}
		}

		if (!authorized) {
			view.print("Invalid username or password! Try again!");
			authorisation();
		}
	}

	public int getMenuItem(int min, int max) {
		int number;

		while(!in.hasNextInt()) {
			in.next();
		}
		number = in.nextInt();
		in.nextLine();
		if (number >= min && number <= max) {
			return number;
		} else {
			return getMenuItem(min, max);
		}
	}

	public void exit() {
		in.close();
		System.exit(0);
	}

}
