package by.epam.library.client.bean;

import java.util.HashMap;
import java.util.Map;

public class ClientUserSession {
    private User user;
    private Map<String, File> files;
    private static ClientUserSession instance;

    private ClientUserSession() {
        this.user = new User();
        this.files = new HashMap<>();
    }

    public static ClientUserSession getInstance() {
        if (instance == null) {
            instance = new ClientUserSession();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public Map<String, File> getFiles() {
        return files;
    }
}
