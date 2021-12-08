package by.epam.library.client.command.impl;

import by.epam.library.client.command.ClientCommand;

public class GetSearchResponseCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        return request;
    }
}
