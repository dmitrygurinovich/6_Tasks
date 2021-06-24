package by.epam.task.logic;

import by.epam.task.entity.Note;
import by.epam.task.view.View;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

public class NotesBaseLogic {
    private final File noteBasePath;
    private final View view;
    private static final Scanner in = new Scanner(System.in);

    public NotesBaseLogic() {
        this.noteBasePath = new File("Task2/src/main/resources/notesbase.json");
        this.view = new View();
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
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder
                .setPrettyPrinting()
                .create();
        return gson.toJson(notes);
    }

    public String getStringFromConsole(String message) {
        String text;
        view.print(message);

        while (!in.hasNextLine()) {
            view.print(message);
            in.next();
        }
        text = in.nextLine();
        return text;
    }
}
