package by.epam.task.server.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class File {
    private Student student;
    private Map<Subject, Integer> progress;

    public File() {
    }

    public File(Student student) {
        this.student = student;
        this.progress = new HashMap<>();
    }

    public File(Student student, Map<Subject, Integer> progress) {
        this.student = student;
        this.progress = progress;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Map<Subject, Integer> getProgress() {
        return progress;
    }

    public void setProgress(Map<Subject, Integer> progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return  student + "\n" +
                (progress.isEmpty() ? "" : "Progress: \n") +
                (progress.containsKey(Subject.MATH) ? "- " + Subject.MATH.name + ": " + progress.get(Subject.MATH) + "\n" : "") +
                (progress.containsKey(Subject.PHYSICS) ? "- " + Subject.PHYSICS.name + ": " + progress.get(Subject.PHYSICS) + "\n" : "") +
                (progress.containsKey(Subject.LITERATURE) ? "- " + Subject.LITERATURE.name + ": " + progress.get(Subject.LITERATURE) + "\n" : "") +
                (progress.containsKey(Subject.GEOGRAPHY) ? "- " + Subject.GEOGRAPHY.name + ": " + progress.get(Subject.GEOGRAPHY) + "\n" : "") +
                (progress.containsKey(Subject.ENGLISH) ? "- " + Subject.ENGLISH.name + ": " + progress.get(Subject.ENGLISH) + "\n" : "");
    }
}
