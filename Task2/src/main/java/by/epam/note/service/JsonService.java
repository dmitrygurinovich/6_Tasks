package by.epam.note.service;

import by.epam.note.bean.Note;

import java.util.ArrayList;

public interface JsonService {
    String objectToJsonObject(ArrayList<Note> notes);
}
