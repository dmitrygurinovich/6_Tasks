package by.epam.library.server.dao;

import by.epam.library.server.entity.User;

import java.util.HashMap;

public interface UsersBaseDAO {
    HashMap<String, User> readUsersFromXml();
    void writeUsersToXml();
    HashMap<String, User> getUsers();
}
