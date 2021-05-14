package by.epam.task.entity;

import by.epam.task.logic.LibraryLogic;

public class Book {
	private static int defaultID = 1;
	private int id;
	private String name;
	private String author;
	private int year;
	private BookType type;
	private String description;
	private final LibraryLogic logic = new LibraryLogic();
	
	public Book() {}
	
	/**
	 * @param name
	 * @param author
	 * @param year
	 * @param type
	 */
	public Book(String name, String author, int year, BookType type) {
		this.id = defaultID++;
		this.name = name;
		this.author = author;
		this.year = year;
		this.type = type;
	}

	/**
	 * @param name
	 * @param author
	 * @param year
	 * @param type
	 * @param description
	 */
	public Book(String name, String author, int year, BookType type, String description) {
		this.id = defaultID++;
		this.name = name;
		this.author = author;
		this.year = year;
		this.type = type;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public BookType getType() {
		return type;
	}

	public void setType(BookType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "№ " + id + "\n" 
				+ (name != null ? "Name: " + name + "\n" : "")
				+ (author != null ? "Author: " + author + "\n" : "")
				+ "Year: " + year + "\n"
				+ (type != null ? "Type: " + type + "\n" : "")
				+ (description != null ? "Description: " + description + "\n" : "");
	}
}
