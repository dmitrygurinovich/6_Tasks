package by.epam.note.command;

import by.epam.note.command.impl.*;
import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<String, Command> COMMANDS = new HashMap<>();
    private static CommandProvider instance;

    private CommandProvider() {
        COMMANDS.put("menu", new MenuCommand());
        COMMANDS.put("show_notes", new ShowAllNotesCommand());
        COMMANDS.put("add_note", new AddNoteCommand());
        COMMANDS.put("delete_note", new DeleteNoteCommand());
        COMMANDS.put("edit_note", new EditNoteCommand());
        COMMANDS.put("search_notes", new SearchNotesCommand());
        COMMANDS.put("exit", new ExitCommand());
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public Command getCommand(String request) {
        return COMMANDS.get(request);
    }
}
