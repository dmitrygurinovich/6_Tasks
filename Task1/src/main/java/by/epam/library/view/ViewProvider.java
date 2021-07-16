package by.epam.library.view;

import by.epam.library.view.impl.DataFromConsoleImpl;
import by.epam.library.view.impl.UserInterfaceImpl;
import by.epam.library.view.impl.ViewImpl;

public class ViewProvider {
    private static ViewProvider instance;

    private final View view = new ViewImpl();
    private final UserInterface userInterface = new UserInterfaceImpl();
    private final DataFromConsole dataFromConsole = new DataFromConsoleImpl();

    private ViewProvider() {}

    public static ViewProvider getInstance(){
        if (instance == null) {
            instance = new ViewProvider();
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
