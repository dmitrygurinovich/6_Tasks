package by.epam.archive.server.service;

import by.epam.archive.server.bean.File;

public interface FileBaseService {
    void addFile(File file);
    void deleteFile(int fileId);
    String getAllFiles();
}
