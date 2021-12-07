package by.epam.library.client.command.impl;

import by.epam.library.client.bean.ClientUserSession;
import by.epam.library.client.bean.File;
import by.epam.library.client.command.ClientCommand;
import by.epam.library.client.presentation.PresentationProvider;
import by.epam.library.client.presentation.View;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFilesCommand implements ClientCommand {
    @Override
    public String execute(String request) {
        String[] params;
        ArrayList<File> result;
        Pattern pattern;
        Matcher matcher;
        ClientUserSession userSession;
        View view;

        params = request.split("&");
        result = new ArrayList<>();
        pattern = Pattern.compile(params[2].toLowerCase(Locale.ROOT));
        userSession = ClientUserSession.getInstance();
        view = PresentationProvider.getInstance().getVIEW();

        for (File file : userSession.getFiles()) {
            StringBuilder fileFieldsForSearch;

            fileFieldsForSearch = new StringBuilder();

            fileFieldsForSearch.append(file.getStudent().getFirstName()).append(file.getStudent().getSecondName());
            matcher = pattern.matcher(fileFieldsForSearch.toString().toLowerCase(Locale.ROOT));

            if (matcher.find()) {
                result.add(file);
            }
        }

        if (result.size() != 0) {
            view.print("Result:");
            for (File file : result) {
                view.print(file);
            }
            return request;
        }

        view.print("No result!");

        return request;
    }
}
