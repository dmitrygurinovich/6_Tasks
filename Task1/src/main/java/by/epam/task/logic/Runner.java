package by.epam.task.logic;

import by.epam.task.entity.Library;
import by.epam.task.view.UserInterface;

public class Runner {
    public Runner() {

    }

    public void run() {
        Library library = new Library();
        UserInterface userInterface = new UserInterface(library);
        userInterface.authorisation();
    }
}
