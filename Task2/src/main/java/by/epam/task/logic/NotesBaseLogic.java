package by.epam.task.logic;

import by.epam.task.entity.Note;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class NotesBaseLogic {
    private final File noteBasePath;

    public NotesBaseLogic() {
        this.noteBasePath = new File("Task2/src/main/resources/notesbase.json");
    }

    public void writeNotesToFile(ArrayList<Note> notes) {
        try (FileWriter writer = new FileWriter(noteBasePath)) {
            writer.write(objectToJsonObject(notes));
            writer.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public ArrayList<Note> readNotesFromFile() {
        ArrayList<Note> notes;
        notes = new ArrayList<>();

        try(FileReader reader = new FileReader(noteBasePath)){
            Gson gson = new Gson();
            Type collectionType = new TypeToken<ArrayList<Note>>(){}.getType();
            notes = gson.fromJson(reader, collectionType);
            return notes;
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return notes;
    }

    public void addNote() {

    }

    public String objectToJsonObject(ArrayList<Note> notes) {
        Gson gson = new Gson();
        return gson.toJson(notes);
    }
}
