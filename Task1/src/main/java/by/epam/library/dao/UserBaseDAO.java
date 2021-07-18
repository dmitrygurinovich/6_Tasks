package by.epam.library.dao;

import by.epam.library.bean.User;

import java.util.ArrayList;

public interface UserBaseDAO {

    void writeUserToFile(User user);
    ArrayList<User> readUsersFromFile();
    boolean isLoginExist(String loginForCheck);

}
