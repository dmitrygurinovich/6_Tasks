package by.epam.library.client.command.impl;

import by.epam.library.client.bean.ClientUserSession;
import by.epam.library.client.command.ClientCommand;
import by.epam.library.client.service.ClientService;
import by.epam.library.client.service.impl.ClientServiceImpl;

public class GetFilesFromServerCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        ClientService clientService;
        ClientUserSession clientUserSession;

        clientService = new ClientServiceImpl();
        clientUserSession = ClientUserSession.getInstance();

        clientUserSession.setFiles(clientService.parseXmlToTheListOfFiles(request));

        return "get_all_files";
    }
}
