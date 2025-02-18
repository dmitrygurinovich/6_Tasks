package by.epam.archive.server.dao.impl;

import by.epam.archive.server.bean.File;
import by.epam.archive.server.bean.Student;
import by.epam.archive.server.bean.Subject;
import by.epam.archive.server.dao.FilesBaseDAO;
import by.epam.archive.server.view.View;
import by.epam.archive.server.view.impl.ViewImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class XMLFilesBaseDAO implements FilesBaseDAO {
    private final String FILES_BASE_PATH = "Task3_Server/resources/files.xml"; //TODO сделать путь относительным!
    private final View VIEW = ViewImpl.getInstance();
    private ArrayList<File> files;

    public XMLFilesBaseDAO() {
        this.files = readFilesFromXml();
    }

    @Override
    public ArrayList<File> readFilesFromXml() {
        ArrayList<File> files = new ArrayList<>();
        List<String> lines;
        StringBuilder xmlDocument;

        try {
            lines = Files.readAllLines(Paths.get(FILES_BASE_PATH), StandardCharsets.UTF_8);
            xmlDocument = new StringBuilder();
            for (String line : lines) {
                xmlDocument.append(line);
            }

            return parseXmlToTheListOfFiles(xmlDocument.toString());

        } catch (IOException exception) {
            VIEW.print("#Server: file not found " + exception.getMessage());
        }
        return files;
    }

    @Override
    public ArrayList<File> parseXmlToTheListOfFiles(String xmlDocument) {
        ArrayList<File> files = new ArrayList<>();
        ArrayList<String> xmlElements = new ArrayList<>();

        Pattern elementsPattern = Pattern.compile("<file>(.*?)</file>");
        Pattern idPattern = Pattern.compile("<id>(.*)</id>");
        Pattern firstNamePattern = Pattern.compile("<first-name>(.*)</first-name>");
        Pattern secondNamePattern = Pattern.compile("<second-name>(.*)</second-name>");
        Pattern agePattern = Pattern.compile("<age>(.*)</age>");
        Pattern groupNumberPattern = Pattern.compile("<group>(.*)</group>");
        Pattern mathPattern = Pattern.compile("<math>(.*)</math>");
        Pattern englishPattern = Pattern.compile("<english>(.*)</english>");
        Pattern geographyPattern = Pattern.compile("<geography>(.*)</geography>");
        Pattern physicsPattern = Pattern.compile("<physics>(.*)</physics>");
        Pattern literaturePattern = Pattern.compile("<literature>(.*)</literature>");

        Matcher matcher = elementsPattern.matcher(xmlDocument.replaceAll("\t", "").replaceAll("\n", ""));
        while (matcher.find()) {
            xmlElements.add(matcher.group());
        }

        for (String element : xmlElements) {
            File file = new File();
            Student student = new Student();
            matcher = idPattern.matcher(element);
            while (matcher.find()) {
                file.setId(Integer.parseInt(matcher.group(1)));
            }

            matcher = firstNamePattern.matcher(element);
            while (matcher.find()) {
                student.setFirstName(matcher.group(1));
            }

            matcher = secondNamePattern.matcher(element);
            while (matcher.find()) {
                student.setSecondName(matcher.group(1));
            }

            matcher = agePattern.matcher(element);
            while (matcher.find()) {
                student.setAge(Integer.parseInt(matcher.group(1)));
            }

            matcher = groupNumberPattern.matcher(element);
            while (matcher.find()) {
                student.setGroupNumber(Integer.parseInt(matcher.group(1)));
            }

            file.setStudent(student);

            matcher = mathPattern.matcher(element);
            while (matcher.find()) {
                file.getProgress().put(Subject.MATH, Integer.parseInt(matcher.group(1)));
            }

            matcher = englishPattern.matcher(element);
            while (matcher.find()) {
                file.getProgress().put(Subject.ENGLISH, Integer.parseInt(matcher.group(1)));
            }

            matcher = geographyPattern.matcher(element);
            while (matcher.find()) {
                file.getProgress().put(Subject.GEOGRAPHY, Integer.parseInt(matcher.group(1)));
            }

            matcher = physicsPattern.matcher(element);
            while (matcher.find()) {
                file.getProgress().put(Subject.PHYSICS, Integer.parseInt(matcher.group(1)));
            }

            matcher = literaturePattern.matcher(element);
            while (matcher.find()) {
                file.getProgress().put(Subject.LITERATURE, Integer.parseInt(matcher.group(1)));
            }

            files.add(file);
        }

        return files;
    }

    @Override
    public StringBuilder getXmlElement(File file) {
        StringBuilder element = new StringBuilder();
        element.append("\t<file>\n");
        element.append("\t\t<id>").append(file.getId()).append("</id>\n");
        element.append("\t\t<student>\n");
        element.append("\t\t\t<first-name>").append(file.getStudent().getFirstName()).append("</first-name>\n");
        element.append("\t\t\t<second-name>").append(file.getStudent().getSecondName()).append("</second-name>\n");
        element.append("\t\t\t<age>").append(file.getStudent().getAge()).append("</age>\n");
        element.append("\t\t\t<group>").append(file.getStudent().getGroupNumber()).append("</group>\n");
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
            if (file.getProgress().containsKey(Subject.LITERATURE)) {
                element.append("\t\t\t<literature>").append(file.getProgress().get(Subject.LITERATURE)).append("</literature>\n");
            }
            element.append("\t\t</progress>\n");
        }
        element.append("\t</file>\n");

        return element;
    }

    @Override
    public String getXmlDocument(ArrayList<File> files) {
        StringBuilder document;

        document = new StringBuilder();

        document.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
        document.append("<files>\n");
        for (File file : files) {
            document.append(getXmlElement(file));
        }
        document.append("</files>");
        return document.toString();
    }

    @Override
    public void writeFilesToXmlFile() {
        try (FileWriter writer = new FileWriter(FILES_BASE_PATH, false)) {
            writer.write(getXmlDocument(this.files));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public ArrayList<File> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<File> files) {
        this.files = files;
    }
}
