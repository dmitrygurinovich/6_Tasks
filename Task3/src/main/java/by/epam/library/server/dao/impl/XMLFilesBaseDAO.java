package by.epam.library.server.dao.impl;

import by.epam.library.server.dao.FilesBaseDAO;
import by.epam.library.server.entity.File;
import by.epam.library.server.service.FileBaseService;
import by.epam.library.server.service.ServiceProvider;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Elements;
import nu.xom.ParsingException;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public final class XMLFilesBaseDAO implements FilesBaseDAO {
    private final String FILES_BASE_PATH = "Task3/src/main/resources/files.xml";
    private final ArrayList<File> files;

    public XMLFilesBaseDAO() {
        this.files = readFilesFromXml();
    }

    @Override
    public ArrayList<File> readFilesFromXml(){
        ArrayList<File> filesList = new ArrayList<>();

        try{
            Document document = new Builder().build(FILES_BASE_PATH);
            Elements elements = document.getRootElement().getChildElements();

            for (int i = 0; i < elements.size(); i++) {
                filesList.add(new File(elements.get(i)));
            }

        } catch (ParsingException | IOException exception) {
            exception.printStackTrace();
        }

        return filesList;
    }

    @Override
    public void writeFilesToXml(){
        FileBaseService fileBaseService = ServiceProvider.getInstance().getFileBaseService();
        try {
            fileBaseService.format(new BufferedOutputStream(new FileOutputStream(FILES_BASE_PATH)), fileBaseService.getXmlDocument());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public ArrayList<File> getFiles() {
        return files;
    }
}
