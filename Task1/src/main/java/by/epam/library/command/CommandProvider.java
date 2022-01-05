package by.epam.library.command;

import by.epam.library.command.impl.*;
import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<String, Command> COMMANDS = new HashMap<>();

    public CommandProvider() {
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

    public Command getCommand(String commandName) {
        return COMMANDS.get(commandName);
    }
}
