package by.epam.archive.client.bean;

import java.util.ArrayList;

public class ClientUserSession {
    private boolean authorized;
    private boolean isFilesReceived;
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

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }

    public boolean isFilesReceived() {
        return isFilesReceived;
    }

    public void setFilesReceived(boolean filesReceived) {
        isFilesReceived = filesReceived;
    }
}
