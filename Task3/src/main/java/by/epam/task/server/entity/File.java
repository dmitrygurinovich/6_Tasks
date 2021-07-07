package by.epam.task.server.entity;

import java.util.Map;

public class File {
    private Student student;
    private Map<Subject, Integer> marks;

    public File() {
    }

    public File(Student student, Map<Subject, Integer> marks) {
        this.student = student;
        this.marks = marks;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Map<Subject, Integer> getMarks() {
        return marks;
    }

    public void setMarks(Map<Subject, Integer> marks) {
        this.marks = marks;
    }


}
