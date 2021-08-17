package by.epam.library.server.dao;

import by.epam.library.server.bean.File;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public interface FilesBaseDAO {
    ArrayList<File> readFilesFromXml() throws ParsingException, IOException;
    void writeFilesToXml() throws IOException;
    ArrayList<File> getFiles();
    void format(OutputStream stream, Document doc);
    Element getXmlElement(File studentFile);
    Document getXmlDocument();
}
