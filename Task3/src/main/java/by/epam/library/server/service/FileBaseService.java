package by.epam.library.server.service;

import by.epam.library.server.bean.File;

public interface FileBaseService {
    void addFile(File file);
    void deleteFile(int fileId);
}
