package by.epam.archive.client.command.impl;

import by.epam.archive.client.bean.ClientUserSession;
import by.epam.archive.client.command.ClientCommand;
import by.epam.archive.client.presentation.PresentationProvider;
import by.epam.archive.client.presentation.View;

public class SessionHasNotFilesCommand implements ClientCommand {
    private final ClientUserSession userSession = ClientUserSession.getInstance();
    private final View view = PresentationProvider.getInstance().getVIEW();

    @Override
    public String execute(String request) {
        String[] params;

        params = request.split("&");
        userSession.setFilesReceived(true);

        if (userSession.isAuthorized()) {
            view.print("\nNo files!\n");
        }

        return params[1];
    }
}
