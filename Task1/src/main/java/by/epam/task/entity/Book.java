package by.epam.task.entity;

import java.util.Objects;

public class Book {
	private static int defaultID = 1;
	private int id;
	private String name;
	private String author;
	private int year;
	private BookType type;
	private String description;

	public Book() {

	}

	public Book(String name, String author, int year, BookType type) {
		this.id = defaultID++;
		this.name = name;
		this.author = author;
		this.year = year;
		this.type = type;
	}

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

	public static void setDefaultID(int defaultID) {
		Book.defaultID = defaultID;
	}

	public static int getDefaultID() {
		return defaultID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Book book = (Book) o;
		return id == book.id &&
				year == book.year &&
				name.equals(book.name) &&
				author.equals(book.author) &&
				type == book.type &&
				description.equals(book.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, author, year, type, description);
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
