package by.epam.task.entity;

import by.epam.task.logic.NotesBaseLogic;

import java.util.ArrayList;

public class NotesBase {
    private ArrayList<Note> notes;

    public NotesBase() {
        NotesBaseLogic logic = new NotesBaseLogic();
        this.notes = logic.readNotesFromFile();
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }
}
