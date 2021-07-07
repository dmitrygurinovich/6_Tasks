package by.epam.task.server.entity;

public class Student {
    private String firstName;
    private String secondName;
    private int age;
    private int groupNumber;

    public Student() {
    }

    public Student(String firstName, String secondName, int age, int groupNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.groupNumber = groupNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    @Override
    public String toString() {
        return  "Student: " + firstName + " " + secondName + "\n" +
                "Age: " + age + " y.o.\n" +
                "Group number: " + groupNumber;
    }
}
