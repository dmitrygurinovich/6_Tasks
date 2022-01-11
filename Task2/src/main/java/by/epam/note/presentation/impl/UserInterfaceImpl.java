package by.epam.note.presentation.impl;

import by.epam.note.controller.Controller;
import by.epam.note.controller.impl.ControllerImpl;
import by.epam.note.presentation.PresentationProvider;
import by.epam.note.presentation.UserInterface;
import by.epam.note.presentation.View;
import by.epam.note.service.ConsoleDataService;
import by.epam.note.service.ServiceProvider;

public class UserInterfaceImpl implements UserInterface {

    @Override
    public void menu() {
        PresentationProvider presentationProvider = PresentationProvider.getInstance();
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        View view = presentationProvider.getView();
        ConsoleDataService consoleDataService = serviceProvider.getConsoleDataService();
        Controller controller = ControllerImpl.getInstance();

        view.print("" +
                "#### MENU ####\n" +
                "1. Show all notes\n" +
                "2. Search\n" +
                "3. Add note\n" +
                "4. Edit note\n" +
                "5. Delete note\n" +
                "0. Exit\n");

        int menuItem = consoleDataService.getNumFromConsole("Enter number 0 - 5:", 0, 5);

        switch (menuItem) {
            case 0:
                controller.doAction("exit");
            case 1:
                controller.doAction("show_notes");
                controller.doAction("menu");
            case 2:
                controller.doAction("search_notes");
                controller.doAction("menu");
            case 3:
                controller.doAction("add_note");
                controller.doAction("menu");
            case 4:
                controller.doAction("edit_note");
                controller.doAction("menu");
            case 5:
                controller.doAction("delete_note");
                controller.doAction("menu");
        }
    }
}
