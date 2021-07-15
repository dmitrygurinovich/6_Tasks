package by.epam.library.bean;

import java.util.Objects;

public class User {
	private static int defaultID = 1;
	private int id;
	private String name;
	private String login;
	private String password;
	private UserRole role;
	private String email;
	
	public User() {}

	public User(int id, String name, String login, String password, UserRole role, String email) {
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.role = role;
		this.email = email;
	}

	public User(String name, String login, String password, UserRole role, String email) {
		this.id = defaultID++;
		this.name = name;
		this.login = login;
		this.password = password;
		this.role = role;
		this.email = email;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return id == user.id &&
				name.equals(user.name) &&
				login.equals(user.login) &&
				password.equals(user.password) &&
				role == user.role &&
				email.equals(user.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, login, password, role, email);
	}

	@Override
	public String toString() {
		return "User ID: " + id + "\n" 
				+ (name != null ? "Name: " + name + "\n" : "")
				+ (login != null ? "Login: " + login + "\n" : "")
				+ (password != null ? "Password: " + password + "\n" : "") 
				+ (role != null ? "Role: " + role + "\n" : "")
				+ (email != null ? "E-mail: " + email + "\n" : "");
	}

}
