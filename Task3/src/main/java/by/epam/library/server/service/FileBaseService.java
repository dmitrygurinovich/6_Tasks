package by.epam.library.server.service;

import by.epam.library.server.entity.File;
import nu.xom.Document;
import nu.xom.Element;

import java.io.OutputStream;

public interface FileBaseService {
    void format(OutputStream stream, Document doc);
    Element getXmlElement(File studentFile);
    Document getXmlDocument();
    void addFile(File file);
    void deleteFile(int fileId);
}
