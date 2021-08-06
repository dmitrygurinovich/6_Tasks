package by.epam.note.presentation.impl;

import by.epam.note.presentation.PresentationProvider;
import by.epam.note.presentation.UserInterface;
import by.epam.note.presentation.View;
import by.epam.note.service.ConsoleDataService;
import by.epam.note.service.NoteBaseService;
import by.epam.note.service.ServiceProvider;

public class UserInterfaceImpl implements UserInterface {
    public UserInterfaceImpl() {}

    @Override
    public void menu() {
        int menuItem;
        View view;
        NoteBaseService noteBaseService;
        ConsoleDataService consoleDataService;

        view = PresentationProvider.getInstance().getView();
        noteBaseService = ServiceProvider.getInstance().getNoteBaseService();
        consoleDataService = ServiceProvider.getInstance().getConsoleDataService();

        view.print("" +
                "#### MENU ####\n" +
                "1. Show all notes\n" +
                "2. Search\n" +
                "3. Add note\n" +
                "4. Edit note\n" +
                "5. Delete note\n" +
                "0. Exit\n");

        menuItem = consoleDataService.getNumFromConsole("Enter number 0 - 5:", 0, 5);

        switch (menuItem) {
            case 0:
                System.exit(0);
            case 1:
                noteBaseService.showAllNotes();
                menu();
            case 2:
                noteBaseService.searchNotes();
                menu();
            case 3:
                noteBaseService.addNote();
                menu();
            case 4:
                noteBaseService.editNote();
                menu();
            case 5:
                noteBaseService.deleteNote();
                menu();
        }
    }
}
