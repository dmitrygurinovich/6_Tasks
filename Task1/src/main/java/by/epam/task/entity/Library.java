package by.epam.task.entity;

import java.util.ArrayList;
import java.util.Arrays;

public class Library {
	private String name;
	private ArrayList<Book> books = new ArrayList<>();
	private ArrayList<User> users = new ArrayList<>();
	
	public Library() {}

	public Library(String name) {
		this.name = name;
	}

	public Library(ArrayList<Book> books) {
		this.books = books;
	}
	
	public Library(String name, Book ... books) {
		this.name = name;
		this.books = new ArrayList<>(Arrays.asList(books));
	}
	
	public Library(Book ... books) {
		this.books = new ArrayList<>(Arrays.asList(books));
	}

	public Library(String name, ArrayList<Book> books, ArrayList<User> users) {
		this.name = name;
		this.books = books;
		this.users = users;
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
