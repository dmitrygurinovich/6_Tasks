package by.epam.task.logic;

import by.epam.task.entity.Note;
import by.epam.task.entity.NotesBase;
import by.epam.task.view.View;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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

    public void addNote(NotesBase notesBase) {
        Note note = new Note();
        String email;

        note.setId(notesBase.getNotes().size() + 1);
        note.setTheme(getStringFromConsole("Enter note's theme:"));

        email = getStringFromConsole("Enter your e-mail:");
        while (!isEmail(email)) {
            email = getStringFromConsole("Wrong e-mail format!\nEnter user's email:");
        }
        note.setEmail(email);
        note.setMessage(getStringFromConsole("Enter note's text:"));
        note.setDate(new GregorianCalendar());

        notesBase.getNotes().add(note);
        writeNotesToFile(notesBase.getNotes());
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

    public boolean isEmail(String email) {
        Pattern emailPattern;
        Matcher matcher;
        boolean isEmail;

        isEmail = false;

        try {
            emailPattern = Pattern.compile(".*@.*\\.\\w*\\S");
            matcher = emailPattern.matcher(email);
            isEmail = matcher.matches();
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
        }

        return isEmail;
    }
}
