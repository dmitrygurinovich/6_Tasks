package by.epam.library.bean;

import by.epam.library.logic.NotesBaseLogic;

import java.util.ArrayList;

public class NotesBase {
    private ArrayList<Note> notes;
    private static NotesBase instance;

    private NotesBase() {
        NotesBaseLogic logic = new NotesBaseLogic();
        this.notes = logic.readNotesFromFile();
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

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }
}
