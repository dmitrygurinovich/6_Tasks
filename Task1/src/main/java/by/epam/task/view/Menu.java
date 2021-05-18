package by.epam.task.view;

import java.util.Scanner;

import by.epam.task.entity.Library;
import by.epam.task.entity.UserRole;
import by.epam.task.logic.LibraryLogic;

public class Menu {
	private final Scanner in = new Scanner(System.in);
	private View view;
	private Library library;
	private LibraryLogic logic;

	public Menu() {}

	public Menu(Library library, View view, LibraryLogic logic) {
		this.library = library;
		this.logic = logic;
		this.view = view;
	}

	public void adminMenu() {
		System.out.println("" +
				"+++ ADMIN MENU +++\n" +
				"1. Show catalog\n" +
				"2. Search book\n" +
				"3. Add book\n" +
				"4. Edit book\n" +
				"0. Exit");
		if (getMenuItem() == 1) {
			view.showBooks(library);
		}
		if(getMenuItem() == 2) {
			// используя регулярки искать совпадения во всех полях обьекта класса Book и возвращать результат книгой
		}
		if(getMenuItem() == 3) {
			//
		}
		if (getMenuItem() == 0) {
			exit();
		}

	}

	public void userMenu() {
		System.out.println("" +
				"+++ USER MENU +++\n" +
				"1. Show catalog\n" +
				"2. Search book\n" +
				"0. Exit");
	}

	public void authorisation(Library library) {
		String login;
		String password;
		boolean authorized = false;

		System.out.println("Enter login: ");
		while (!in.hasNextLine()) {
			in.next();
			System.out.println("Enter login: ");
		}
		login = in.nextLine();

		System.out.println("Enter password: ");
		while (!in.hasNextLine()) {
			in.next();
			System.out.println("Enter password: ");
		}
		password = in.nextLine();

		for (int i = 0; i < library.getUsers().size(); i++) {
			if (library.getUsers().get(i).getLogin().equals(login)) {
				if (library.getUsers().get(i).getPassword().equals(password)) {
					authorized = true;
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
			System.err.println("Invalid username or password! Try again!");
			authorisation(library);
		}
	}

	public int getMenuItem() {
		while (!in.hasNextInt()) {
			in.next();
		}
		return in.nextInt();
	}

	public void exit() {
		in.close();
		System.exit(0);
	}

}
