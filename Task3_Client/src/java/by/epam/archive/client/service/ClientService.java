package by.epam.archive.client.service;

import by.epam.archive.client.bean.File;

import java.util.ArrayList;

public interface ClientService {
    ArrayList<File> parseXmlToTheListOfFiles(String xmlDocument);
    String getAllFilesInXml();
    String searchFiles(String request);
    void deleteFile(int id);
}
