package by.epam.task.entity;

public class User {
	private static int defaultID = 1;
	private int id;
	private String name;
	private String login;
	private String password;
	private UserRole role;
	private String email;
	
	public User() {}
	
	/**
	 * @param name
	 * @param login
	 * @param password
	 * @param role
	 * @param email
	 */
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
	public String toString() {
		return "User ID: " + id + "\n" 
				+ (name != null ? "Name: " + name + "\n" : "")
				+ (login != null ? "Login: " + login + "\n" : "")
				+ (password != null ? "Password: " + password + "\n" : "") 
				+ (role != null ? "Role: " + role + "\n" : "")
				+ (email != null ? "E-mail: " + email + "\n" : "");
	}
	
	
	
}
