package by.epam.library.client.command.impl;

import by.epam.library.client.command.ClientCommand;
import by.epam.library.client.service.ClientService;
import by.epam.library.client.service.ServiceProvider;


public class SearchFilesCommand implements ClientCommand {

    @Override
    public String execute(String request) {
        ClientService clientService;

        clientService = ServiceProvider.getInstance().getClientService();

        return clientService.searchFiles(request);
    }
}
