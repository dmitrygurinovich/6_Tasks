package by.epam.task.entity;

public class Book {
	private static int defaultID = 1;
	private int id;
	private String name;
	private String autor;
	private int year;
	private BookType type;
	
	public Book() {}
	
	/**
	 * @param name
	 * @param autor
	 * @param year
	 * @param type
	 */
	public Book(String name, String autor, int year, BookType type) {
		this.id = defaultID++;
		this.name = name;
		this.autor = autor;
		this.year = year;
		this.type = type;
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
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

	@Override
	public String toString() {
		return "№ " + id + "\n" 
				+ (name != null ? "Name: " + name + "\n" : "")
				+ (autor != null ? "Autor: " + autor + "\n" : "") 
				+ "Year: " + year + "\n"
				+ (type != null ? "Type: " + type + "\n" : "");
	}


	
}
