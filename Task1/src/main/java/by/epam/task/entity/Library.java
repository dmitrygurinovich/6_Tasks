package by.epam.task.entity;

import by.epam.task.logic.LibraryLogic;
import by.epam.task.logic.UsersBaseLogic;

import java.util.ArrayList;

public class Library {
	private String name;
	private final LibraryLogic libraryLogic = new LibraryLogic();
	private final UsersBaseLogic usersBaseLogic = new UsersBaseLogic();
	private ArrayList<Book> books = libraryLogic.readBooksFromFile();
	private ArrayList<User> users = new ArrayList<>();
	
	public Library() {}

	public Library(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	
}
