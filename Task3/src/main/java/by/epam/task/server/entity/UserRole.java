package by.epam.task.server.entity;

public enum UserRole {
    USER("User"),
    ADMINISTRATOR("Administrator");

    String name;

    UserRole(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User role: " + name;
    }
}
