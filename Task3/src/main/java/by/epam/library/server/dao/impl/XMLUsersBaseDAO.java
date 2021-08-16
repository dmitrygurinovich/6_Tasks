package by.epam.library.server.dao.impl;

import by.epam.library.server.dao.UsersBaseDAO;
import by.epam.library.server.entity.User;
import by.epam.library.server.service.ServiceProvider;
import by.epam.library.server.service.UserBaseService;
import nu.xom.*;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class XMLUsersBaseDAO implements UsersBaseDAO {
    private static final String USERS_BASE_PATH = "Task3/src/main/resources/users.xml";
    private final HashMap<String, User> users;

    public XMLUsersBaseDAO(){
        this.users = readUsersFromXml();
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    @Override
    public HashMap<String, User> readUsersFromXml() {
        HashMap<String, User> users = new HashMap<>();
        try {
            Document document = new Builder()
                    .build(USERS_BASE_PATH);

            Elements elements = document.getRootElement().getChildElements();

            for (Element element : elements) {
                users.put(element.getFirstChildElement("username").getValue(), new User(element));
            }
        } catch (ParsingException | IOException exception) {
            exception.printStackTrace();
        }


        return users;
    }

    @Override
    public void writeUsersToXml() {
        UserBaseService userBaseService = ServiceProvider.getInstance().getUserBaseService();
        try {
            userBaseService.format(new BufferedOutputStream(new FileOutputStream(USERS_BASE_PATH)), userBaseService.getXmlDocument());
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }
}
