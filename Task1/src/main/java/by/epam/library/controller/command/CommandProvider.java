package by.epam.library.controller.command;

import by.epam.library.controller.command.impl.AdminMenuCommand;
import by.epam.library.controller.command.impl.AuthorizationCommand;
import by.epam.library.controller.command.impl.UserMenuCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put("authorization", new AuthorizationCommand());
        commands.put("admin_menu", new AdminMenuCommand());
        commands.put("user_menu", new UserMenuCommand());
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }
}
