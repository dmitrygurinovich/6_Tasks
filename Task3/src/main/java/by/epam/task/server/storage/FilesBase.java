package by.epam.task.server.storage;

import by.epam.task.server.entity.File;
import by.epam.task.server.logic.FilesBaseLogic;
import nu.xom.ParsingException;

import java.io.IOException;
import java.util.ArrayList;

public class FilesBase {
    private ArrayList<File> files;

    public FilesBase(){
        FilesBaseLogic logic = new FilesBaseLogic();
        try {
            this.files = logic.readFilesFromXml();
        } catch (ParsingException | IOException exception) {
            exception.printStackTrace();
        }
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
