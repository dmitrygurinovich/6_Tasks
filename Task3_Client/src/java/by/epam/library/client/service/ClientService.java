package by.epam.library.client.service;

import by.epam.library.client.bean.File;

import java.util.ArrayList;

public interface ClientService {
    ArrayList<File> parseXmlToTheListOfFiles(String xmlDocument);
    String getAllFilesInXml();
    String getXmlDocumentFromArrayList(ArrayList<File> files);
    StringBuilder getXmlElementFromFile(File file);
    String addFile();
    String editFile();
    File editFileProgress(File file);
    String searchFiles();
    String deleteFile();
}
