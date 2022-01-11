package by.epam.note.service.impl;

import by.epam.note.bean.Note;
import by.epam.note.bean.NotesBase;
import by.epam.note.dao.NotesBaseDAO;
import by.epam.note.dao.impl.JSONNotesBaseDAO;
import by.epam.note.presentation.PresentationProvider;
import by.epam.note.presentation.UserInterface;
import by.epam.note.presentation.View;
import by.epam.note.service.ConsoleDataService;
import by.epam.note.service.NoteBaseService;
import by.epam.note.service.ServiceProvider;
import by.epam.note.validation.Validation;
import by.epam.note.validation.impl.ValidationImpl;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NotesBaseServiceImpl implements NoteBaseService {

    @Override
    public void showAllNotes() {
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        UserInterface userInterface = presentationProvider.getUserInterface();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ConsoleDataService consoleDataService = serviceProvider.getConsoleDataService();
        NotesBase notesBase = NotesBase.getInstance();
        View view = presentationProvider.getView();

        showNotesThemes();

        int menuItem = consoleDataService.getNumFromConsole("Enter note's number or \"0\" " +
                "for entering to the main menu", 0, notesBase.getNotes().size());

        if (menuItem == 0) {
            userInterface.menu();
        }

        view.print(notesBase.getNotes().get(menuItem - 1));
        userInterface.menu();
    }

    @Override
    public void showNotesThemes() {
        NotesBase notesBase = NotesBase.getInstance();
        ArrayList<Note> notes = notesBase.getNotes();
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        View view = presentationProvider.getView();

        for (Note note : notes) {
            view.print(note.getId() + ". " + note.getTheme());
        }
    }

    @Override
    public void addNote() {
        Note note = new Note();
        Validation validation = new ValidationImpl();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ConsoleDataService consoleDataService = serviceProvider.getConsoleDataService();
        NotesBase notesBase = NotesBase.getInstance();
        NotesBaseDAO notesBaseDAO = JSONNotesBaseDAO.getInstance();

        note.setId(notesBase.getNotes().size() + 1);
        note.setTheme(consoleDataService.getStringFromConsole("Enter note's theme:"));

        String email = consoleDataService.getStringFromConsole("Enter your e-mail:");
        while (validation.itIsNotEmail(email)) {
            email = consoleDataService.getStringFromConsole("Wrong e-mail format!\nEnter user's email:");
        }
        note.setEmail(email);
        note.setMessage(consoleDataService.getStringFromConsole("Enter note's text:"));
        note.setDate(new GregorianCalendar());

        notesBase.getNotes().add(note);
        notesBaseDAO.writeNotesToFile(notesBase.getNotes());
    }

    @Override
    public void editNote() {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ConsoleDataService consoleDataService = serviceProvider.getConsoleDataService();
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        UserInterface userInterface = presentationProvider.getUserInterface();
        NotesBase notesBase = NotesBase.getInstance();

        int menuItem = consoleDataService.getNumFromConsole("Choose note for editing. " +
                "Enter note's number or \"0\" for enter to the main menu: ", 0, notesBase.getNotes().size());

        if (menuItem == 0) {
            userInterface.menu();
        } else {
            editNotesFields(menuItem);
        }
    }

    @Override
    public void deleteNote() {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ConsoleDataService consoleDataService = serviceProvider.getConsoleDataService();
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        UserInterface userInterface = presentationProvider.getUserInterface();
        NotesBase notesBase = NotesBase.getInstance();
        View view = presentationProvider.getView();
        NotesBaseDAO notesBaseDAO = JSONNotesBaseDAO.getInstance();

        int menuItem = consoleDataService.getNumFromConsole("Enter note's number which you want to delete or \"0\" " +
                "for entering to the main menu", 0, notesBase.getNotes().size());

        if (menuItem == 0) {
            userInterface.menu();
        } else {
            view.print(notesBase.getNotes().get(menuItem - 1).getTheme() + " will be delete!");
            int confirmation = consoleDataService.getNumFromConsole("1. Confirm\n0. Cancel ang go to the main " +
                    "menu", 0, 1);

            if (confirmation == 0) {
                userInterface.menu();
            } else {
                notesBase.getNotes().remove(menuItem - 1);
                changeNotesId(notesBase);
                notesBaseDAO.writeNotesToFile(notesBase.getNotes());
            }
        }
    }

    @Override
    public void searchNotes() {
        ArrayList<Note> searchResult = new ArrayList<>();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ConsoleDataService consoleDataService = serviceProvider.getConsoleDataService();
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        UserInterface userInterface = presentationProvider.getUserInterface();
        NotesBase notesBase = NotesBase.getInstance();
        View view = presentationProvider.getView();

        String keyword = consoleDataService.getStringFromConsole("Enter keyword for searching: ");
        Pattern pattern = Pattern.compile(keyword.toLowerCase(Locale.ROOT));

        int menuItem = consoleDataService.getNumFromConsole("" +
                "1. Search in themes\n" +
                "2. Search in text\n" +
                "3. Search in themes and texts\n" +
                "0. Go to the main menu", 0, 3);

        if (menuItem == 0) {
            userInterface.menu();
        }

        if (menuItem == 1) {
            for (Note note : notesBase.getNotes()) {
                Matcher matcher = pattern.matcher(note.getTheme());
                if (matcher.find()) {
                    searchResult.add(note);
                }
            }
            sortByTheme(searchResult);
        }
        if (menuItem == 2) {
            for (Note note : notesBase.getNotes()) {
                Matcher matcher = pattern.matcher(note.getMessage());
                if (matcher.find()) {
                    searchResult.add(note);
                }
            }
            sortByText(searchResult);
        }
        if (menuItem == 3) {
            for (Note note : notesBase.getNotes()) {
                String concatenateNoteFields = (note.getTheme() + " " + note.getMessage()).toLowerCase(Locale.ROOT);
                Matcher matcher = pattern.matcher(concatenateNoteFields);
                if (matcher.find()) {
                    searchResult.add(note);
                }
            }
            sortByTheme(searchResult);
        }

        if (searchResult.size() != 0) {
            view.print("### RESULT ###");
            for (Note note : searchResult) {
                view.print(note);
            }
        } else {
            view.print("No result!");
        }
    }

    @Override
    public void sortByTheme(ArrayList<Note> notes) {
        Collections.sort(notes, new Comparator<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                return o1.getTheme().compareTo(o2.getTheme());
            }
        });
    }

    @Override
    public void sortByText(ArrayList<Note> notes) {
        Collections.sort(notes, new Comparator<Note>() {
            @Override
            public int compare(Note o1, Note o2) {
                return o1.getMessage().compareTo(o2.getMessage());
            }
        });
    }

    @Override
    public void editNotesFields(int noteNumber) {
        Validation validation = new ValidationImpl();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ConsoleDataService consoleDataService = serviceProvider.getConsoleDataService();
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        UserInterface userInterface = presentationProvider.getUserInterface();
        NotesBase notesBase = NotesBase.getInstance();
        NotesBaseDAO notesBaseDAO = JSONNotesBaseDAO.getInstance();
        View view = presentationProvider.getView();

        view.print("## You're editing note: ##");
        view.print(notesBase.getNotes().get(noteNumber - 1));
        view.print("#### EDITING MENU ####\n" +
                "1. Edit note's theme\n" +
                "2. Edit e-mail\n" +
                "3. Edit  note's text\n" +
                "0. Save and go to the main menu\n");

        int menuItem = consoleDataService.getNumFromConsole("Enter number 0-4:", 0, 4);

        if (menuItem == 0) {
            notesBaseDAO.writeNotesToFile(notesBase.getNotes());
            userInterface.menu();
        }

        switch (menuItem) {
            case 1:
                notesBase.getNotes().get(noteNumber - 1).setTheme(consoleDataService.getStringFromConsole("Enter new note's theme:"));
                notesBase.getNotes().get(noteNumber - 1).setDate(new GregorianCalendar());
                editNotesFields(noteNumber);
            case 2:
                String email = consoleDataService.getStringFromConsole("Enter new e-mail:");
                if (validation.itIsNotEmail(email)) {
                    email = consoleDataService.getStringFromConsole("Wrong format! Enter new e-mail:");
                }
                notesBase.getNotes().get(noteNumber - 1).setEmail(email);
                notesBase.getNotes().get(noteNumber - 1).setDate(new GregorianCalendar());
                editNotesFields(noteNumber);
            case 3:
                notesBase.getNotes().get(noteNumber - 1).setMessage("Enter new note's text:");
                notesBase.getNotes().get(noteNumber - 1).setDate(new GregorianCalendar());
                editNotesFields(noteNumber);
        }
    }

    @Override
    public void changeNotesId(NotesBase notesBase) {
        ArrayList<Note> notes = notesBase.getNotes();

        for (int i = 0; i < notes.size(); i++) {
            notesBase.getNotes().get(i).setId(i + 1);
        }
    }
}