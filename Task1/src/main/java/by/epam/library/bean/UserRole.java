package by.epam.library.bean;

public enum UserRole {
	ADMINISTRATOR,
	USER;
	
	@Override
	public String toString() {
		return super.toString().substring(0, 1).toUpperCase().concat(super.toString().substring(1).toLowerCase());
	}
}
