package by.epam.archive.server.service;

public interface FileBaseService {
    String addFile(String request);
    String editFile(String request);
    String filesXmlToString();
    String deleteFile(String request);
    String getAllFiles(String request);
}
