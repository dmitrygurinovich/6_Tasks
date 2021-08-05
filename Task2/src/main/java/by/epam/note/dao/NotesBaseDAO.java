package by.epam.note.dao;

import by.epam.note.bean.Note;

import java.util.ArrayList;

public interface NotesBaseDAO {
    ArrayList<Note> readNotesFromFile();
    void writeNotesToFile(ArrayList<Note> notes);
}
