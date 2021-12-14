package by.epam.library.client.command.impl;

import by.epam.library.client.bean.ClientUserSession;
import by.epam.library.client.bean.File;
import by.epam.library.client.command.ClientCommand;
import by.epam.library.client.presentation.PresentationProvider;
import by.epam.library.client.presentation.View;

public class ShowAllFilesCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        ClientUserSession clientUserSession;
        View view;

        clientUserSession = ClientUserSession.getInstance();
        view = PresentationProvider.getInstance().getVIEW();

        for (File file : clientUserSession.getFiles()) {
            view.print(file);
        }

        return "get_all_files";
    }
}
