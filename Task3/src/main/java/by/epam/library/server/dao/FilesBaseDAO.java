package by.epam.library.server.dao;

import by.epam.library.server.bean.File;
import nu.xom.ParsingException;

import java.io.IOException;
import java.util.ArrayList;

public interface FilesBaseDAO {
    ArrayList<File> readFilesFromXml() throws ParsingException, IOException;
    ArrayList<File> parseXmlToTheListOfFiles(String xml);
    ArrayList<File> getFiles();
    StringBuilder getXmlElement(File file);
    String getXmlDocument(ArrayList<File> files);
    void writeFilesToXmlFile();
}
