package by.epam.task.main;

import by.epam.task.entity.NotesBase;
import by.epam.task.view.UserInterface;

public class Runner {

    public Runner() {

    }

    public void run() {
        NotesBase notesBase = new NotesBase();
        UserInterface userInterface = new UserInterface(notesBase);
        userInterface.menu();
    }

}
