package by.epam.library.server.storage;

import by.epam.library.server.entity.User;
import by.epam.library.server.logic.UserBaseLogic;
import nu.xom.ParsingException;

import java.io.IOException;
import java.util.HashMap;

public class UsersBase {
    private static UsersBase instance;
    private HashMap<String, User> users;

    private UsersBase() throws ParsingException, IOException {
        UserBaseLogic logic = new UserBaseLogic();
        this.users = logic.readUsersFromXml();
    }

    public static UsersBase getInstance() throws ParsingException, IOException {
        if (instance == null) {
            instance = new UsersBase();
        }
        return instance;
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }
}
