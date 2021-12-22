package by.epam.archive.server.command;

import by.epam.archive.server.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class ServerCommandProvider {
    private final Map<String, ServerCommand> COMMANDS = new HashMap<>();
    private static ServerCommandProvider instance;

    private ServerCommandProvider() {
        COMMANDS.put("get_all_files", new GetAllFilesCommand());
        COMMANDS.put("show_all_files", new GetAllFilesCommand());
        COMMANDS.put("delete_file", new DeleteFileCommand());
        COMMANDS.put("edit_file", new EditFileCommand());
        COMMANDS.put("add_file", new AddFileCommand());
        COMMANDS.put("authorization", new AuthorizationCommand());
    }

    public static ServerCommandProvider getInstance() {
        if (instance == null) {
            instance = new ServerCommandProvider();
        }
        return instance;
    }

    public ServerCommand getCommand(String request) {
        return COMMANDS.get(request);
    }
}
