package by.epam.library.client.bean;

import java.util.ArrayList;

public class ClientUserSession {
    private User user;
    private ArrayList<File> files;
    private static ClientUserSession instance;

    private ClientUserSession() {
        this.user = new User();
        this.files = new ArrayList<>();
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

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }
}
