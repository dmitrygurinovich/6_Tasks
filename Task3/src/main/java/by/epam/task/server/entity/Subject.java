package by.epam.task.server.entity;

public enum Subject {
    MATH ("Math"),
    PHYSICS ("Physics"),
    LITERATURE ("Literature"),
    GEOGRAPHY ("Geography"),
    ENGLISH ("English");

    String name;

    Subject(String name) {
        this.name = name;
    }
}
