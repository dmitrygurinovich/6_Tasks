package by.epam.library.client.service;

import by.epam.library.client.bean.File;

import java.util.ArrayList;

public interface ClientService {
    ArrayList<File> parseXmlToTheListOfFiles(String xmlDocument);
    String getAllFiles();
    String getXmlDocument(ArrayList<File> files);
    StringBuilder getXmlElement(File file);
    String addFile();
    String editFile();
    File editProgress(File file);
    String searchFiles();
    String deleteFile();
}
