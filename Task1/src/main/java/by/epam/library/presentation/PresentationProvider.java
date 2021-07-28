package by.epam.library.presentation;

import by.epam.library.presentation.impl.DataFromConsoleImpl;
import by.epam.library.presentation.impl.UserInterfaceImpl;
import by.epam.library.presentation.impl.ViewImpl;

public final class PresentationProvider {
    private static PresentationProvider instance;

    private final View view = new ViewImpl();
    private final UserInterface userInterface = new UserInterfaceImpl();
    private final DataFromConsole dataFromConsole = new DataFromConsoleImpl();

    public PresentationProvider() {}

    public static PresentationProvider getInstance(){
        if (instance == null) {
            instance = new PresentationProvider();
        }
        return instance;
    }

    public View getView() {
        return view;
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public DataFromConsole getDataFromConsole() {
        return dataFromConsole;
    }
}
