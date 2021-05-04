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

		for (User user : library.getUsers()) {
			
		}
	}

	public void exit() {
		in.close();
		System.exit(0);
	}

}
