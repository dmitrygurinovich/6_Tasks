package by.epam.task.entity;

import by.epam.task.logic.LibraryLogic;

import java.util.ArrayList;
import java.util.Arrays;

public class Library {
	private String name;
	private final LibraryLogic logic = new LibraryLogic();
	private ArrayList<Book> books = logic.readBooksFromFile();
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
