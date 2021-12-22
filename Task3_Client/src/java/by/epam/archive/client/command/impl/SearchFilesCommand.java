package by.epam.archive.client.command.impl;

import by.epam.archive.client.service.ClientService;
import by.epam.archive.client.service.ServiceProvider;
import by.epam.archive.client.command.ClientCommand;


public class SearchFilesCommand implements ClientCommand {

    @Override
    public String execute(String request) {
        ClientService clientService;

        clientService = ServiceProvider.getInstance().getClientService();

        return clientService.searchFiles(request);
    }
}
