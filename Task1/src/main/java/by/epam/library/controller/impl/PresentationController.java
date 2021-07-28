package by.epam.library.controller.impl;

import by.epam.library.controller.Controller;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.presentation.UserInterface;
import by.epam.library.presentation.View;

public class PresentationController implements Controller {
    private final PresentationProvider presentationProvider = PresentationProvider.getInstance();
    private final UserInterface userInterface = presentationProvider.getUserInterface();
    private final View view = presentationProvider.getView();

    @Override
    public void doAction(String request) {
        switch (request) {
            case "admin_menu":
                userInterface.adminMenu();
            case "user_menu":
                userInterface.userMenu();
            case "authorization":
                userInterface.authorization();
            case "exit":
                userInterface.exit();
            case "show_books":
                view.showBooks();
        }
    }
}
