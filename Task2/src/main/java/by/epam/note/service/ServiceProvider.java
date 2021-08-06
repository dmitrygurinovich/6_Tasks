package by.epam.note.service;

import by.epam.note.service.impl.ConsoleDataServiceImpl;
import by.epam.note.service.impl.JsonServiceImpl;
import by.epam.note.service.impl.NotesBaseServiceImpl;

public class ServiceProvider {
    private static ServiceProvider instance;
    private final ConsoleDataService consoleDataService = new ConsoleDataServiceImpl();
    private final NoteBaseService noteBaseService = new NotesBaseServiceImpl();
    private final JsonService jsonService = new JsonServiceImpl();

    private ServiceProvider() {}

    public static ServiceProvider getInstance() {
        if(instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }

    public ConsoleDataService getConsoleDataService() {
        return consoleDataService;
    }

    public NoteBaseService getNoteBaseService() {
        return noteBaseService;
    }

    public JsonService getJsonService() {
        return jsonService;
    }
}
