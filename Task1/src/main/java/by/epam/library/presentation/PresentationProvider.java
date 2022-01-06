package by.epam.library.presentation;

import by.epam.library.presentation.impl.DataFromConsoleImpl;
import by.epam.library.presentation.impl.UserInterfaceImpl;
import by.epam.library.presentation.impl.ViewImpl;

public final class PresentationProvider {
    private static PresentationProvider instance;
    private final View VIEW = new ViewImpl();
    private final UserInterface USER_INTERFACE = new UserInterfaceImpl();
    private final DataFromConsole DATA_FROM_CONSOLE = new DataFromConsoleImpl();

    private PresentationProvider() {

    }

    public static PresentationProvider getInstance(){
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

    public DataFromConsole getDataFromConsole() {
        return DATA_FROM_CONSOLE;
    }
}
