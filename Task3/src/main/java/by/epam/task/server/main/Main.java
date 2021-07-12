package by.epam.task.server.main;

import by.epam.task.server.entity.File;
import by.epam.task.server.entity.FilesBase;
import by.epam.task.server.entity.Student;
import by.epam.task.server.entity.Subject;
import by.epam.task.server.logic.FilesBaseLogic;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {

        Student first = new Student("Ivan", "Ivanov", 20, 2);
        Student second = new Student("Petr", "Petrov", 25, 1);

        HashMap<Subject, Integer> progress = new HashMap<>();

        progress.put(Subject.MATH, 5);
        progress.put(Subject.ENGLISH, 4);
        progress.put(Subject.PHYSICS, 5);
        progress.put(Subject.GEOGRAPHY, 4);
        progress.put(Subject.LITERATURE, 4);

        File file1 = new File(first, progress);
        File file2 = new File(second, progress);

        FilesBase base = new FilesBase();
        FilesBaseLogic logic = new FilesBaseLogic();

        base.getFiles().add(file1);
        base.getFiles().add(file2);

        logic.writeFilesToXml(base);

        for (File file : logic.readFilesFromXml()) {
            System.out.println(file);
        }

    }
}
