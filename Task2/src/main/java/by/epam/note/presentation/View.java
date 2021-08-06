package by.epam.note.presentation;

import by.epam.note.bean.Note;

public interface View {
    void print(Note note);
    void print(String text);
}
