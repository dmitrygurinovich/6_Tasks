package by.epam.library.view;

import by.epam.library.bean.Note;

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
