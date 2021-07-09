package by.epam.task.server.main;

import by.epam.task.server.entity.File;
import by.epam.task.server.entity.FilesBase;
import by.epam.task.server.entity.Student;
import by.epam.task.server.entity.Subject;
import by.epam.task.server.logic.FilesBaseLogic;
import nu.xom.Document;
import nu.xom.Element;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        Student student = new Student("Dmitry", "Gurinovich", 32, 2);
        Student student1 = new Student("Inna", "Gurinovich", 31, 2);
        HashMap<Subject, Integer> marks = new HashMap<>();

        marks.put(Subject.MATH, 5);
        marks.put(Subject.ENGLISH, 5);
        marks.put(Subject.LITERATURE, 4);
        marks.put(Subject.GEOGRAPHY, 5);
        marks.put(Subject.PHYSICS, 5);

        File file1 = new File(student, marks);
        File file2 = new File(student1, marks);

        FilesBase base = new FilesBase();
        base.getFiles().add(file1);
        base.getFiles().add(file2);

        FilesBaseLogic logic = new FilesBaseLogic();

        Element root = new Element("root");

        for (File file : base.getFiles()) {
            root.appendChild(logic.getXmlElement(file));
        }

        Document document = new Document(root);

        FilesBaseLogic.format(System.out, document);
        FilesBaseLogic.format(new BufferedOutputStream(new FileOutputStream("Task3/src/main/resources/files.xml")), document);
    }
}
