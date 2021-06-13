package by.epam.task.view;

import by.epam.task.entity.Note;

public class View {
    public View() {

    }

    public void print(Note note) {
        System.out.println(note);
    }

    public void print(String text) {
        System.out.println(text);
    }
}
