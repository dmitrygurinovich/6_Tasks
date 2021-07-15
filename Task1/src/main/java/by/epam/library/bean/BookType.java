package by.epam.library.bean;

public enum BookType {
	PAPER_BOOK, 
	ELECTRONIC_BOOK;

	@Override
	public String toString() {
		return super.toString().split("_")[0].substring(0, 1).toUpperCase()
				.concat(super.toString().split("_")[0].substring(1).toLowerCase())
				.concat(" ")
				.concat(super.toString().split("_")[1].toLowerCase());
	}
}
