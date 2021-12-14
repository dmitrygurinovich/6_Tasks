package by.epam.library.client.command.impl;

import by.epam.library.client.command.ClientCommand;
import by.epam.library.client.presentation.PresentationProvider;
import by.epam.library.client.presentation.View;

public class EditFileCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        View view;

        view = PresentationProvider.getInstance().getVIEW();
        view.print("File has been edited!");

        return "edited";
    }
}
