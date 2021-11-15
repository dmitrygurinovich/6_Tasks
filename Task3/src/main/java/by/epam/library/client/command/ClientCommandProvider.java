package by.epam.library.client.command;

import by.epam.library.client.command.impl.SearchFilesCommand;
import by.epam.library.client.command.impl.ShowAllFilesCommand;

import java.util.HashMap;
import java.util.Map;

public class ClientCommandProvider {
    private static ClientCommandProvider instance;
    private final Map<String, ClientCommand> COMMANDS = new HashMap<>();

    private ClientCommandProvider() {
        COMMANDS.put("show_all_files", new ShowAllFilesCommand());
        COMMANDS.put("search_files", new SearchFilesCommand());
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
