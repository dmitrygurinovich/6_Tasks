package by.epam.library.server.command;

import by.epam.library.server.command.impl.ShowFilesCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put("show_files", new ShowFilesCommand());
    }

    public Command getCommand(String request) {
        return commands.get(request);
    }
}
