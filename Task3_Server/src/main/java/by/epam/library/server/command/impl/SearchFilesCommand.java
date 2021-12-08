package by.epam.library.server.command.impl;

import by.epam.library.server.command.ServerCommand;

public class SearchFilesCommand implements ServerCommand {
    @Override
    public String execute(String request) {
        String[] params;
        StringBuilder response;

        params = request.split("&");
        response = new StringBuilder();

        response.append("search_result").append("&").append(params[2]);

        return response.toString();
    }
}
