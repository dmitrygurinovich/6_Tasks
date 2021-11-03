package by.epam.library.server.command.impl;

import by.epam.library.server.bean.File;
import by.epam.library.server.command.Command;
import by.epam.library.server.dao.DAOProvider;
import by.epam.library.server.dao.FilesBaseDAO;

import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetFilesBySearchCommand implements Command {
    @Override
    public String execute(String request) {
        String[] params = request.split("\\s+");
        ArrayList<File> result;
        FilesBaseDAO filesBaseDAO;
        Pattern pattern;
        Matcher matcher;

        filesBaseDAO = DAOProvider.getInstance().getFilesBaseDAO();
        result = new ArrayList<>();
        pattern = Pattern.compile(params[2].toLowerCase(Locale.ROOT));

        for(File file : filesBaseDAO.getFiles()) {
            StringBuilder fileFieldsForSearch;

            fileFieldsForSearch = new StringBuilder();
            fileFieldsForSearch.append(file.getStudent().getFirstName()).append(file.getStudent().getSecondName());
            matcher = pattern.matcher(fileFieldsForSearch.toString().toLowerCase(Locale.ROOT));

            if (matcher.find()) {
                result.add(file);
            }
        }

        if (result.size() != 0) {
            return filesBaseDAO.getXmlDocument(result).toString().replace("\n", "");
        }

        return "\"" + params[2] + "\" not found!";
    }
}
