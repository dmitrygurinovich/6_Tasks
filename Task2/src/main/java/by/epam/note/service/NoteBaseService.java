package by.epam.note.service;

import by.epam.note.bean.Note;
import by.epam.note.bean.NotesBase;

import java.util.ArrayList;

public interface NoteBaseService {
    void showAllNotes();
    void showNotesThemes();
    void addNote();
    void editNote();
    void deleteNote();
    void searchNotes();
    void sortByTheme(ArrayList<Note> notes);
    void sortByText(ArrayList<Note> notes);
    void editNotesFields(int noteNumber);
    void changeNotesId(NotesBase notesBase);
}
