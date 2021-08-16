package by.epam.note.dao.impl;

import by.epam.note.bean.Note;
import by.epam.note.dao.NotesBaseDAO;
import by.epam.note.service.JsonService;
import by.epam.note.service.ServiceProvider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JSONNotesBaseDAO implements NotesBaseDAO {
    private static final File NOTES_BASE_PATH = new File("Task2/src/main/resources/notesbase.json");
    private static JSONNotesBaseDAO instance;

    private JSONNotesBaseDAO() {}

    public static JSONNotesBaseDAO getInstance() {
        if (instance == null) {
            instance = new JSONNotesBaseDAO();
        }
        return instance;
    }

    @Override
    public ArrayList<Note> readNotesFromFile() {
        ArrayList<Note> notes;

        notes = new ArrayList<>();

        try (FileReader reader = new FileReader(NOTES_BASE_PATH)) {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<ArrayList<Note>>() {
            }.getType();
            notes = gson.fromJson(reader, collectionType);
            return notes;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return notes;
    }

    @Override
    public void writeNotesToFile(ArrayList<Note> notes) {
        JsonService jsonService;

        jsonService = ServiceProvider.getInstance().getJsonService();

        try (FileWriter writer = new FileWriter(NOTES_BASE_PATH)) {
            writer.write(jsonService.objectToJsonObject(notes));
            writer.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
