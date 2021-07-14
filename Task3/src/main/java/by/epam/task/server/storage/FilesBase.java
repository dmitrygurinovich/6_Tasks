package by.epam.task.server.storage;

import by.epam.task.server.entity.File;
import by.epam.task.server.logic.FilesBaseLogic;
import nu.xom.ParsingException;

import java.io.IOException;
import java.util.ArrayList;

public final class FilesBase {
    private static FilesBase instance;
    private ArrayList<File> files;

    private FilesBase(){
        FilesBaseLogic logic = new FilesBaseLogic();
        try {
            this.files = logic.readFilesFromXml();
        } catch (ParsingException | IOException exception) {
            exception.printStackTrace();
        }
    }

    public static FilesBase getInstance() {
        if (instance == null) {
            instance = new FilesBase();
        }
        return instance;
    }

    public FilesBase(ArrayList<File> files) {
        this.files = files;
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }
}
