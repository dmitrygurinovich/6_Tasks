package by.epam.library.server.dao;

import by.epam.library.server.entity.File;
import nu.xom.ParsingException;

import java.io.IOException;
import java.util.ArrayList;

public interface FilesBaseDAO {
    ArrayList<File> readFilesFromXml() throws ParsingException, IOException;
    void writeFilesToXml() throws IOException;
    ArrayList<File> getFiles();
}
