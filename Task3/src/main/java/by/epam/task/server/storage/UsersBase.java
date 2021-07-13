package by.epam.task.server.storage;

import by.epam.task.server.entity.User;

import java.util.HashMap;

public class UsersBase {
    private HashMap<String, User> users;

    public UsersBase() {
        this.users = new HashMap<>();
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }
}
