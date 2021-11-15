package by.epam.library.server.command;

import by.epam.library.server.command.impl.AddFileCommand;
import by.epam.library.server.command.impl.DeleteFileCommand;
import by.epam.library.server.command.impl.GetAllFilesCommand;
import by.epam.library.server.command.impl.GetFilesBySearchCommand;

import java.util.HashMap;
import java.util.Map;

public class ServerCommandProvider {
    private final Map<String, ServerCommand> commands = new HashMap<>();
    private static ServerCommandProvider instance;

    private ServerCommandProvider() {
        commands.put("get_all_files", new GetAllFilesCommand());
        commands.put("delete_file", new DeleteFileCommand());
        commands.put("add_file", new AddFileCommand());
        commands.put("search", new GetFilesBySearchCommand());
    }

    public static ServerCommandProvider getInstance() {
        if (instance == null) {
            instance = new ServerCommandProvider();
        }
        return instance;
    }

    public ServerCommand getCommand(String request) {
        return commands.get(request);
    }
}
