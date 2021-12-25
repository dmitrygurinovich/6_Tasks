package by.epam.archive.client.command.impl;

import by.epam.archive.client.service.ClientService;
import by.epam.archive.client.service.impl.ClientServiceImpl;
import by.epam.archive.client.bean.ClientUserSession;
import by.epam.archive.client.command.ClientCommand;

public class GetFilesFromServerCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        ClientService clientService;
        ClientUserSession clientUserSession;

        clientService = new ClientServiceImpl();
        clientUserSession = ClientUserSession.getInstance();

        clientUserSession.setFilesReceived(true);
        clientUserSession.setFiles(clientService.parseXmlToTheListOfFiles(request));

        return "get_all_files";
    }
}
