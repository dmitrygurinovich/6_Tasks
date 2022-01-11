package by.epam.archive.server.service.impl;

import by.epam.archive.server.bean.User;
import by.epam.archive.server.dao.DAOProvider;
import by.epam.archive.server.dao.UsersBaseDAO;
import by.epam.archive.server.service.UserBaseService;
import by.epam.archive.server.view.View;
import by.epam.archive.server.view.impl.ViewImpl;

public class UserBaseServiceImpl implements UserBaseService {

    @Override
    public void addUser(User user) {
        UsersBaseDAO usersBaseDAO;

        usersBaseDAO = DAOProvider.getInstance().getUsersBaseDAO();
        usersBaseDAO.getUsers().put(user.getUsername(), user);
        usersBaseDAO.writeUsersToXml();
    }

    @Override
    public String authorization(String request) {
        String[] params = request.split("&");
        DAOProvider daoProvider = DAOProvider.getInstance();
        UsersBaseDAO usersBaseDAO = daoProvider.getUsersBaseDAO();
        StringBuilder authorizationResponse = new StringBuilder("authorization&log_in&");
        View view = ViewImpl.getInstance();

        String username = params[1];
        String password = params[2];

        if (usersBaseDAO.getUsers().containsKey(params[1])) {

            if (usersBaseDAO.getUsers().get(username).getPassword().equals(password)) {
                view.print("#Server: " + username + " is authorized.");
                return authorizationResponse
                        .append(usersBaseDAO.getUsers().get(params[1]).getUsername())
                        .append("&")
                        .append(usersBaseDAO.getUsers().get(params[1]).getRole().toString())
                        .toString();
            }
        }
        return "error&authorization_error";
    }
}
