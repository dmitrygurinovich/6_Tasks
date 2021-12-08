package by.epam.library.client.command;

import by.epam.library.client.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class ClientCommandProvider {
    private static ClientCommandProvider instance;
    private final Map<String, ClientCommand> COMMANDS = new HashMap<>();

    private ClientCommandProvider() {
        COMMANDS.put("get_all_files", new GetAllFilesCommand());
        COMMANDS.put("show_all_files", new ShowAllFilesCommand());
        COMMANDS.put("edit_file", new EditFileCommand());
        COMMANDS.put("search_files", new SearchFilesCommand());
        COMMANDS.put("authorization", new AuthorizationCommand());
        COMMANDS.put("log_in", new LogInCommand());
        COMMANDS.put("menu", new GetMenuCommand());
        COMMANDS.put("filed", new AuthorizationFailedCommand());
        COMMANDS.put("search_result", new GetSearchResponseCommand());
    }

    public static ClientCommandProvider getInstance() {
        if (instance == null) {
            instance = new ClientCommandProvider();
        }
        return instance;
    }

    public ClientCommand getCommand(String request) {
        return COMMANDS.get(request);
    }
}
