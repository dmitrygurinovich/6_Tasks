package by.epam.task.view;

import java.util.Scanner;

import by.epam.task.entity.Library;
import by.epam.task.entity.User;
import by.epam.task.entity.UserRole;

public class Menu {
	private Scanner in = new Scanner(System.in);

	public Menu() {
	}

	public void adminMenu() {
		System.out.println("+++ ADMIN MENU +++");
	}

	public void userMenu() {
		System.out.println("+++ USER MENU +++");
	}

	public void createUser(Library library) {
		String name;
		String login;
		String password;
		UserRole role;

		System.out.println("Enter name: ");
		while (!in.hasNextLine()) {
			in.next();
			System.out.println("Enter name: ");
		}
		name = in.nextLine();

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

		System.out.println("Set up user role: ");
		System.out.println("1. User: ");
		System.out.println("2. Administrator: ");
		while (!in.hasNextInt() && (in.nextInt() != 1 || in.nextInt() != 2)) {
			in.next();
			System.out.println("Set up user role: ");
			System.out.println("1. User");
			System.out.println("2. Administrator");
		}

		if (in.nextInt() == 1) {
			role = UserRole.USER;
		} else {
			role = UserRole.ADMINISTRATOR;
		}

		library.getUsers().add(new User(name, login, password, role));
		System.out.println("User " + name + " added!");
	}

	public void autorization(Library library) {
		String login;
		String password;
		boolean autorized = false;

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
					autorized = true;
					if (library.getUsers().get(i).getRole().equals(UserRole.ADMINISTRATOR)) {
						adminMenu();
					} else {
						userMenu();
					}
					break;
				}
			}
		}

		if (!autorized) {
			System.err.println("Invalid username or password! Try again!");
			autorization(library);
		}
	}

	public void exit() {
		in.close();
		System.exit(0);
	}

}
