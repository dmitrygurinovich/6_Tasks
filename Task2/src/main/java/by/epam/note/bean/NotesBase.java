package by.epam.note.bean;

import by.epam.note.dao.impl.FileNotesBaseDAO;

import java.util.ArrayList;

public class NotesBase {
    private ArrayList<Note> notes;
    private static NotesBase instance;

    private NotesBase() {
        this.notes = FileNotesBaseDAO.getInstance().readNotesFromFile();
    }

    public static NotesBase getInstance() {
        if (instance == null) {
            instance = new NotesBase();
        }
        return instance;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }
}
