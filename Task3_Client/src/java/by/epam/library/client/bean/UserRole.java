package by.epam.library.client.bean;

public enum UserRole {
    USER,
    ADMINISTRATOR;

    @Override
    public String toString() {
        return super.toString().substring(0, 1).toUpperCase()
                .concat(super.toString().substring(1).toLowerCase());
    }
}
