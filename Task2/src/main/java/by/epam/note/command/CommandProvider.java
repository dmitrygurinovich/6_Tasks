package by.epam.note.command;

import by.epam.note.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put("menu", new MenuCommand());
        commands.put("show_notes", new ShowAllNotesCommand());
        commands.put("add_note", new AddNoteCommand());
        commands.put("delete_note", new DeleteNoteCommand());
        commands.put("edit_note", new EditNoteCommand());
        commands.put("search_notes", new SearchNotesCommand());
        commands.put("exit", new ExitCommand());
    }

    public Command getCommand(String request) {
        return commands.get(request);
    }
}
