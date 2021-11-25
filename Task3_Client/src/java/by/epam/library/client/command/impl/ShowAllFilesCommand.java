package by.epam.library.client.command.impl;

import by.epam.library.client.bean.ClientUserSession;
import by.epam.library.client.command.ClientCommand;
import by.epam.library.client.presentation.PresentationProvider;
import by.epam.library.client.presentation.View;
import by.epam.library.client.service.ClientService;
import by.epam.library.client.service.impl.ClientServiceImpl;

public class ShowAllFilesCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        ClientService clientService;
        ClientUserSession clientUserSession;
        View view;

        clientService = new ClientServiceImpl();
        clientUserSession = ClientUserSession.getInstance();
        view = PresentationProvider.getInstance().getVIEW();

        clientUserSession.setFiles(clientService.parseXmlToTheListOfFiles(request));

        clientUserSession.getFiles().forEach(view::print);

        return "return null";
    }
}
