package by.epam.task.entity;

import by.epam.task.logic.LibraryLogic;
import by.epam.task.logic.UsersBaseLogic;

import java.util.ArrayList;

public class Library {
	private LibraryLogic libraryLogic;
	private UsersBaseLogic usersBaseLogic;
	private ArrayList<Book> books;
	private ArrayList<User> users;
	
	public Library() {
		this.libraryLogic = new LibraryLogic();
		this.usersBaseLogic = new UsersBaseLogic();
		this.books = libraryLogic.readBooksFromFile();
		this.users = usersBaseLogic.readUsersFromFile();
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
