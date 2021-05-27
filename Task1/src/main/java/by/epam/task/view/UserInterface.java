package by.epam.task.view;

import java.util.Scanner;

import by.epam.task.entity.Library;
import by.epam.task.entity.User;
import by.epam.task.entity.UserRole;
import by.epam.task.logic.LibraryLogic;
import by.epam.task.logic.UserLogic;
import by.epam.task.logic.UsersBaseLogic;

public class UserInterface {
	private final Scanner in;
	private final View view;
	private final Library library;
	private final LibraryLogic libraryLogic;
	private final UserLogic userLogic;
	private final UsersBaseLogic usersBaseLogic;
	private User user;


	public UserInterface() {
		this.library = new Library();
		this.in = new Scanner(System.in);
		this.view = new View();
		this.libraryLogic = new LibraryLogic();
		this.userLogic = new UserLogic();
		this.usersBaseLogic = new UsersBaseLogic();
		this.user = new User();
	}

	public void adminMenu() {
		int minMenuItem;
		int maxMenuItem;

		minMenuItem = 0;
		maxMenuItem = 6;

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
			view.showBooksByPages(library, 1);
		}
		if (menuItem == 2) {
			// TODO реализовать поиск
		}
		if (menuItem == 3) {
			libraryLogic.addBook(library);
			adminMenu();
		}
		if (menuItem == 4) {
			// TODO реализовать правку книги
		}
		if (menuItem == 5) {
			usersBaseLogic.addUser(library);
			adminMenu();
		}
		if (menuItem == 0) {
			exit();
		}
	}

	public void userMenu() {
		int minMenuItem;
		int maxMenuItem;

		minMenuItem = 0;
		maxMenuItem = 4;

		int item;

		view.print("" +
				"+++ USER MENU +++\n" +
				"1. Show catalog\n" +
				"2. Search book\n" +
				"3. Suggest new book\n"+
				"0. Exit");

		item = getMenuItem(minMenuItem, maxMenuItem);

		if (item == 1) {
			view.showBooksByPages(library, 1);
			userMenu();
		}
		if (item == 2) {
			// TODO реализовать поиск
		}
		if (item == 3) {
			userLogic.suggestNewBook(user);
			userMenu();
		}
		if (item == 0) {
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
