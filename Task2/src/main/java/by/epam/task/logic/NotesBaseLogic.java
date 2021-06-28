package by.epam.task.logic;

import by.epam.task.entity.Note;
import by.epam.task.entity.NotesBase;
import by.epam.task.view.UserInterface;
import by.epam.task.view.View;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    public void showAllNotes(NotesBase notesBase, UserInterface userInterface) {
        int menuItem;

        showNotesThemes(notesBase);

        menuItem = getNumFromConsole("Enter note's number or \"0\" for entering to the main menu", 0, notesBase.getNotes().size());
        if (menuItem == 0) {
            userInterface.menu();
        }
        view.print(notesBase.getNotes().get(menuItem - 1));
        userInterface.menu();
    }

    public void showNotesThemes(NotesBase notesBase) {
        for (Note note : notesBase.getNotes()) {
            view.print(note.getId() + ". " + note.getTheme());
        }
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

        try (FileReader reader = new FileReader(noteBasePath)) {
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

    public void addNote(NotesBase notesBase) {
        Note note = new Note();
        String email;

        note.setId(notesBase.getNotes().size() + 1);
        note.setTheme(getStringFromConsole("Enter note's theme:"));

        email = getStringFromConsole("Enter your e-mail:");
        while (itIsNotEmail(email)) {
            email = getStringFromConsole("Wrong e-mail format!\nEnter user's email:");
        }
        note.setEmail(email);
        note.setMessage(getStringFromConsole("Enter note's text:"));
        note.setDate(new GregorianCalendar());

        notesBase.getNotes().add(note);
        writeNotesToFile(notesBase.getNotes());
    }

    public void editNote(NotesBase notesBase, UserInterface userInterface) {
        int menuItem;
        menuItem = getNumFromConsole("Choose note for editing. Enter note's number or \"0\" for enter to the main menu: ", 0, notesBase.getNotes().size());

        if (menuItem == 0) {
            userInterface.menu();
        } else {
            editNotesFields(notesBase, userInterface, menuItem);
        }
    }

    public void deleteNote(NotesBase notesBase, UserInterface userInterface) {
        int menuItem;
        int confirmation;

        menuItem = getNumFromConsole("Enter note's number which you want to delete or \"0\" for entering to the main menu", 0, notesBase.getNotes().size());

        if (menuItem == 0) {
            userInterface.menu();
        } else {
            view.print(notesBase.getNotes().get(menuItem - 1).getTheme() + " will be delete!");
            confirmation = getNumFromConsole("1. Confirm\n0. Cancel ang go to the main menu", 0, 1);

            if (confirmation == 0) {
                userInterface.menu();
            } else {
                notesBase.getNotes().remove(menuItem - 1);
                changeNotesId(notesBase);
                writeNotesToFile(notesBase.getNotes());
            }
        }

    }

    public void editNotesFields(NotesBase notesBase, UserInterface userInterface, int noteNumber) {
        int menuItem;
        String email;

        view.print("## You're editing note: ##");
        view.print(notesBase.getNotes().get(noteNumber - 1));
        view.print("#### EDITING MENU ####\n" +
                "1. Edit note's theme\n" +
                "2. Edit e-mail\n" +
                "3. Edit  note's text\n" +
                "0. Save and go to the main menu\n");

        menuItem = getNumFromConsole("Enter number 0-4:", 0, 4);

        if (menuItem == 0) {
            writeNotesToFile(notesBase.getNotes());
            userInterface.menu();
        }

        switch (menuItem) {
            case 1:
                notesBase.getNotes().get(noteNumber - 1).setTheme(getStringFromConsole("Enter new note's theme:"));
                notesBase.getNotes().get(noteNumber - 1).setDate(new GregorianCalendar());
                editNotesFields(notesBase, userInterface, noteNumber);
            case 2:
                email = getStringFromConsole("Enter new e-mail:");
                if (itIsNotEmail(email)) {
                    email = getStringFromConsole("Wrong format! Enter new e-mail:");
                }
                notesBase.getNotes().get(noteNumber - 1).setEmail(email);
                notesBase.getNotes().get(noteNumber - 1).setDate(new GregorianCalendar());
                editNotesFields(notesBase, userInterface, noteNumber);
            case 3:
                notesBase.getNotes().get(noteNumber - 1).setMessage("Enter new note's text:");
                notesBase.getNotes().get(noteNumber - 1).setDate(new GregorianCalendar());
                editNotesFields(notesBase, userInterface, noteNumber);
        }
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

    public int getNumFromConsole(String message, int min, int max) {
        int number;
        view.print(message);
        while (!in.hasNextInt()) {
            view.print(message);
            in.next();
        }
        number = in.nextInt();
        in.nextLine();

        if (number >= min && number <= max) {
            return number;
        } else {
            return getNumFromConsole(message, min, max);
        }
    }

    public boolean itIsNotEmail(String email) {
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

        return !isEmail;
    }

    public void changeNotesId(NotesBase notesBase) {
        for (int i = 0; i < notesBase.getNotes().size(); i++) {
            notesBase.getNotes().get(i).setId(i + 1);
        }
    }
}
