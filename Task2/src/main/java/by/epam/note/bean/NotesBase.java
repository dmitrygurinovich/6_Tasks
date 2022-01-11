package by.epam.note.bean;

import by.epam.note.dao.impl.JSONNotesBaseDAO;

import java.util.ArrayList;

public class NotesBase {
    private final JSONNotesBaseDAO JSON_NOTES_BASE_DAO = JSONNotesBaseDAO.getInstance();
    private final ArrayList<Note> notes = JSON_NOTES_BASE_DAO.readNotesFromFile();
    private static NotesBase instance;

    private NotesBase() {

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
