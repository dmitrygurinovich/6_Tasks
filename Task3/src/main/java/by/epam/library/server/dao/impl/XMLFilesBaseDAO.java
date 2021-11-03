package by.epam.library.server.dao.impl;

import by.epam.library.server.bean.File;
import by.epam.library.server.bean.Subject;
import by.epam.library.server.dao.FilesBaseDAO;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Elements;
import nu.xom.ParsingException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public final class XMLFilesBaseDAO implements FilesBaseDAO {
    private final String FILES_BASE_PATH = "Task3/src/main/resources/files.xml";
    private final ArrayList<File> files;

    public XMLFilesBaseDAO() {
        this.files = readFilesFromXml();
    }

    //TODO: create custom XML parser!
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
    public StringBuilder getXmlElement(File file) {
        StringBuilder element = new StringBuilder();
        element.append("\t<file>\n");
            element.append("\t\t<id>").append(file.getId()).append("</id>\n");
            element.append("\t\t<student>\n");
                element.append("\t\t\t<first-name>").append(file.getStudent().getFirstName()).append("</first-name>\n");
                element.append("\t\t\t<second-name>").append(file.getStudent().getSecondName()).append("</second-name>\n");
            element.append("\t\t</student>\n");
            if (!file.getProgress().isEmpty()) {
                element.append("\t\t<progress>\n");
                if (file.getProgress().containsKey(Subject.MATH)) {
                    element.append("\t\t\t<math>").append(file.getProgress().get(Subject.MATH)).append("</math>\n");
                }
                if (file.getProgress().containsKey(Subject.ENGLISH)) {
                    element.append("\t\t\t<english>").append(file.getProgress().get(Subject.ENGLISH)).append("</english>\n");
                }
                if (file.getProgress().containsKey(Subject.GEOGRAPHY)) {
                    element.append("\t\t\t<geography>").append(file.getProgress().get(Subject.GEOGRAPHY)).append("</geography>\n");
                }
                if (file.getProgress().containsKey(Subject.PHYSICS)) {
                    element.append("\t\t\t<physics>").append(file.getProgress().get(Subject.PHYSICS)).append("</physics>\n");
                }
                if (file.getProgress().containsKey(Subject.LITERATURE)){
                    element.append("\t\t\t<literature>").append(file.getProgress().get(Subject.LITERATURE)).append("</literature>\n");
                }
                element.append("\t\t</progress>\n");
            }
            element.append("\t</file>\n");

        return element;
    }

    @Override
    public StringBuilder getXmlDocument(ArrayList<File> files) {
        StringBuilder document = new StringBuilder();
        document.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
        document.append("<files>\n");
        for (File file : files) {
            document.append(getXmlElement(file));
        }
        document.append("</files>");
        return document;
    }

    @Override
    public void writeFilesToXmlFile() {
        try(FileWriter writer = new FileWriter(FILES_BASE_PATH, false)) {
            writer.write(getXmlDocument(this.files).toString());
        } catch (IOException exception) {
           exception.printStackTrace();
        }
    }

    public ArrayList<File> getFiles() {
        return files;
    }
}
