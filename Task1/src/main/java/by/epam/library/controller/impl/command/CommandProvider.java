package by.epam.library.controller.impl.command;

import by.epam.library.controller.impl.command.impl.*;
import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    public static CommandProvider instance;
    private final Map<String, Command> COMMANDS = new HashMap<>();

    private CommandProvider() {
        COMMANDS.put("authorization", new AuthorizationCommand());
        COMMANDS.put("admin_menu", new AdminMenuCommand());
        COMMANDS.put("add_book", new AddBookCommand());
        COMMANDS.put("edit_book", new EditBookCommand());
        COMMANDS.put("add_user", new AddUserCommand());
        COMMANDS.put("user_menu", new UserMenuCommand());
        COMMANDS.put("suggest_book", new SuggestNewBookCommand());
        COMMANDS.put("show_books", new ShowBooksCommand());
        COMMANDS.put("search_books", new SearchBooksByKeywordCommand());
        COMMANDS.put("exit", new ExitCommand());
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public Command getCommand(String commandName) {
        return COMMANDS.get(commandName);
    }
}
