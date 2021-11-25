package by.epam.library.client.command.impl;

import by.epam.library.client.command.ClientCommand;

public class ShowAllFilesCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        String[] params;

        params = request.split("&");

        System.out.println(params[2]);
        return "return nul";
    }
}
