package by.epam.note.presentation;

import by.epam.note.presentation.impl.UserInterfaceImpl;
import by.epam.note.presentation.impl.ViewImpl;

public class PresentationProvider {
    private static PresentationProvider instance;
    private final View VIEW = new ViewImpl();
    private final UserInterface USER_INTERFACE = new UserInterfaceImpl();

    private PresentationProvider() {

    }

    public static PresentationProvider getInstance() {
        if (instance == null) {
            instance = new PresentationProvider();
        }
        return instance;
    }

    public View getView() {
        return VIEW;
    }

    public UserInterface getUserInterface() {
        return USER_INTERFACE;
    }
}
