package by.epam.note.service;

import by.epam.note.service.impl.ConsoleDataServiceImpl;
import by.epam.note.service.impl.JsonServiceImpl;
import by.epam.note.service.impl.NotesBaseServiceImpl;

public class ServiceProvider {
    private static ServiceProvider instance;
    private final ConsoleDataService CONSOLE_DATA_SERVICE = new ConsoleDataServiceImpl();
    private final NoteBaseService NOTES_BASE_SERVICE = new NotesBaseServiceImpl();
    private final JsonService JSON_SERVICE = new JsonServiceImpl();

    private ServiceProvider() {

    }

    public static ServiceProvider getInstance() {
        if(instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }

    public ConsoleDataService getConsoleDataService() {
        return CONSOLE_DATA_SERVICE;
    }

    public NoteBaseService getNoteBaseService() {
        return NOTES_BASE_SERVICE;
    }

    public JsonService getJsonService() {
        return JSON_SERVICE;
    }
}
