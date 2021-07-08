package by.epam.task.server.main;

import by.epam.task.server.entity.File;
import by.epam.task.server.entity.FilesBase;
import by.epam.task.server.entity.Student;
import by.epam.task.server.entity.Subject;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Student student = new Student("Gurinovich", "Dmitry", 32, 2);
        Student student1 = new Student("Gurinovich", "Inna", 31, 2);
        Map<Subject, Integer> marks = new HashMap<>();

        marks.put(Subject.MATH, 5);
        marks.put(Subject.ENGLISH, 5);
        marks.put(Subject.LITERATURE, 4);
        marks.put(Subject.GEOGRAPHY, 5);
        marks.put(Subject.PHYSICS, 5);

        File file = new File(student, marks);
        File file1 = new File(student1);

        FilesBase base = new FilesBase();
        base.getFiles().add(file);
        base.getFiles().add(file1);


        System.out.println(base.getFiles().get(0));
        System.out.println(base.getFiles().get(1));
    }
}
