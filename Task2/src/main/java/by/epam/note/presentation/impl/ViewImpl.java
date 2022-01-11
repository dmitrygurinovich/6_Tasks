package by.epam.note.presentation.impl;

import by.epam.note.bean.Note;
import by.epam.note.presentation.View;

public class ViewImpl implements View {

    @Override
    public void print(Note note) {
        System.out.println(note);
    }

    @Override
    public void print(String text) {
        System.out.println(text);
    }
}
