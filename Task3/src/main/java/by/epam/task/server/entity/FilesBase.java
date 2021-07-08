package by.epam.task.server.entity;

import java.util.ArrayList;

public class FilesBase {
    private ArrayList<File> files;

    public FilesBase() {
        this.files = new ArrayList<>();
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }
}
