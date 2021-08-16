package by.epam.library.server.entity;

import by.epam.library.server.service.ServiceProvider;
import by.epam.library.server.service.UserBaseService;
import nu.xom.Element;

public class User {
    private String username;
    private String password;
    private UserRole role;

    public User() {}

    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(Element user) {
        UserBaseService userBaseService = ServiceProvider.getInstance().getUserBaseService();
        this.username = user.getFirstChildElement("username").getValue();
        this.password = userBaseService.decryptUserPassword(user.getFirstChildElement("password").getValue());
        if (user.getFirstChildElement("user-role").getValue().equals("Admin")) {
            this.role = UserRole.ADMINISTRATOR;
        } else {
            this.role = UserRole.USER;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return  "User's name: " + username + '\n' +
                "Password: " + password + '\n' +
                "User role: " + role + "\n";
    }
}
