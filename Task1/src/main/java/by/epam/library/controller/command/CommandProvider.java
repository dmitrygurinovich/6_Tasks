package by.epam.library.controller.command;

import by.epam.library.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        // authorization command
        commands.put("authorization", new AuthorizationCommand());

        // admin's menu commands
        commands.put("admin_menu", new AdminMenuCommand());
        commands.put("add_book", new AddBookCommand());
        commands.put("edit_book", new EditBookCommand());
        commands.put("add_user", new AddUserCommand());

        // user's menu commands
        commands.put("user_menu", new UserMenuCommand());
        commands.put("suggest_book", new SuggestNewBookCommand());

        // user's and admin's commands
        commands.put("show_books", new ShowBooksCommand());
        commands.put("search_books", new SearchBooksByKeywordCommand());
        commands.put("exit", new ExitCommand());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
