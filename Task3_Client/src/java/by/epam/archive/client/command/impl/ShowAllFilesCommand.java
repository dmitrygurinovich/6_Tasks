package by.epam.archive.client.command.impl;

import by.epam.archive.client.presentation.PresentationProvider;
import by.epam.archive.client.presentation.View;
import by.epam.archive.client.bean.ClientUserSession;
import by.epam.archive.client.bean.File;
import by.epam.archive.client.command.ClientCommand;

public class ShowAllFilesCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        ClientUserSession clientUserSession;
        View view;

        clientUserSession = ClientUserSession.getInstance();
        view = PresentationProvider.getInstance().getVIEW();

        if (clientUserSession.getFiles().isEmpty()) {
            view.print("\nNo files!\n");
        } else {
            for (File file : clientUserSession.getFiles()) {
                view.print(file);
            }
        }

        return "get_all_files";
    }
}
