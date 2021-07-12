package by.epam.task.server.entity;

import nu.xom.Element;

import java.util.HashMap;

public class File {
    private int id;
    private Student student;
    private HashMap<Subject, Integer> progress;
    private static int defaultId = 1;

    public File() {
    }

    public File(Student student) {
        this.student = student;
        this.progress = new HashMap<>();
        this.id = defaultId++;
    }

    public File(int id,Student student, HashMap<Subject, Integer> progress) {
        this.id = id;
        this.student = student;
        this.progress = progress;
    }

    public File(Student student, HashMap<Subject, Integer> progress) {
        this.id = defaultId++;
        this.student = student;
        this.progress = progress;
    }

    public File(Element file) {
        this.student = new Student(
                file.getFirstChildElement("first-name").getValue(),
                file.getFirstChildElement("second-name").getValue(),
                Integer.parseInt(file.getFirstChildElement("age").getValue()),
                Integer.parseInt(file.getFirstChildElement("group").getValue())
        );

        this.progress = new HashMap<>();

        if (file.getFirstChildElement("math") != null) {
            this.progress.put(Subject.MATH, Integer.parseInt(file.getFirstChildElement("math").getValue()));
        }
        if (file.getFirstChildElement("english") != null) {
            this.progress.put(Subject.ENGLISH, Integer.parseInt(file.getFirstChildElement("english").getValue()));
        }
        if (file.getFirstChildElement("geography") != null) {
            this.progress.put(Subject.GEOGRAPHY, Integer.parseInt(file.getFirstChildElement("geography").getValue()));
        }
        if (file.getFirstChildElement("physics") != null) {
            this.progress.put(Subject.PHYSICS, Integer.parseInt(file.getFirstChildElement("physics").getValue()));
        }
        if (file.getFirstChildElement("literature") != null) {
            this.progress.put(Subject.LITERATURE, Integer.parseInt(file.getFirstChildElement("literature").getValue()));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public HashMap<Subject, Integer> getProgress() {
        return progress;
    }

    public void setProgress(HashMap<Subject, Integer> progress) {
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
