package by.epam.library.client.command.impl;

import by.epam.library.client.command.ClientCommand;
import by.epam.library.client.presentation.PresentationProvider;
import by.epam.library.client.presentation.View;

public class AddFileCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        View view;

        view = PresentationProvider.getInstance().getVIEW();

        view.print("File has been added!");

        return request;
    }
}
