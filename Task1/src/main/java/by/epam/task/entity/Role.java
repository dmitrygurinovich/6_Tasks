package by.epam.task.entity;

public enum Role {
	ADMINISTRATOR,
	USER;
	
	@Override
	public String toString() {
		return super.toString().substring(0, 1).toUpperCase().concat(super.toString().substring(1).toLowerCase());
	}
}
