package by.epam.task.view;

import java.util.Scanner;

import by.epam.task.entity.Library;
import by.epam.task.entity.UserRole;
import by.epam.task.logic.LibraryLogic;
import by.epam.task.logic.UserLogic;
import by.epam.task.logic.UsersBaseLogic;

public class UserInterface {
	private final Scanner in;
	private final Library library;

	public UserInterface() {
		this.library = new Library();
		this.in = new Scanner(System.in);
	}

	public void adminMenu() {
		LibraryLogic libraryLogic;
		UsersBaseLogic usersBaseLogic;
		View view;

		int minMenuItem;
		int maxMenuItem;

		minMenuItem = 0;
		maxMenuItem = 6;
		view = new View();

		int menuItem;

		view.print("" +
				"+++ ADMIN MENU +++\n" +
				"1. Show catalog\n" +
				"2. Search book\n" +
				"3. Add book\n" +
				"4. Edit book\n" +
				"5. Add user\n" +
				"6. Add book's description\n" +
				"0. Exit");

		menuItem = getMenuItem(minMenuItem, maxMenuItem);
		if (menuItem == 1){
			view.showBooks(library);
		}
		if (menuItem == 2) {
			// TODO реализовать поиск
		}
		if (menuItem == 3) {
			libraryLogic = new LibraryLogic();
			libraryLogic.addBook(library);
			adminMenu();
		}
		if (menuItem == 4) {
			// TODO реализовать правку книги
		}
		if (menuItem == 5) {
			usersBaseLogic = new UsersBaseLogic();
			usersBaseLogic.addUser(library);
			adminMenu();
		}
		if (menuItem == 0) {
			exit();
		}
	}

	public void userMenu() {
		UserLogic userLogic;
		View view;
		int minMenuItem;
		int maxMenuItem;
		int item;

		minMenuItem = 0;
		maxMenuItem = 4;
		view = new View();

		view.print("" +
				"+++ USER MENU +++\n" +
				"1. Show catalog\n" +
				"2. Search book\n" +
				"3. Suggest new book\n"+
				"0. Exit");

		item = getMenuItem(minMenuItem, maxMenuItem);

		if (item == 1) {
			view.showBooks(library);
			userMenu();
		}
		if (item == 2) {
			// TODO реализовать поиск
		}
		if (item == 3) {
			userLogic = new UserLogic();
			userLogic.suggestNewBook(library.getAuthorizedUser());
			userMenu();
		}
		if (item == 0) {
			exit();
		}

	}

	public void authorisation() {
		View view;
		String login;
		String password;
		boolean authorized;

		view = new View();
		authorized = false;

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
					library.setAuthorizedUser(library.getUsers().get(i));
					if (library.getAuthorizedUser().getRole().equals(UserRole.ADMINISTRATOR)) {
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
