package by.epam.archive.server.dao;

import by.epam.archive.server.bean.File;

import java.util.ArrayList;

public interface FilesBaseDAO {
    ArrayList<File> readFilesFromXml();
    ArrayList<File> parseXmlToTheListOfFiles(String xml);
    ArrayList<File> getFiles();
    StringBuilder getXmlElement(File file);
    String getXmlDocument(ArrayList<File> files);
    void writeFilesToXmlFile();
    void setFiles(ArrayList<File> files);
}
