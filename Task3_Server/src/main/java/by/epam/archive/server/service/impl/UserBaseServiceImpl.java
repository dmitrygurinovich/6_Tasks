package by.epam.archive.server.service.impl;

import by.epam.archive.server.bean.User;
import by.epam.archive.server.dao.DAOProvider;
import by.epam.archive.server.dao.UsersBaseDAO;
import by.epam.archive.server.service.UserBaseService;

public class UserBaseServiceImpl implements UserBaseService {

    public UserBaseServiceImpl() {}

    @Override
    public void addUser(User user) {
        UsersBaseDAO usersBaseDAO;

        usersBaseDAO = DAOProvider.getInstance().getUsersBaseDAO();
        usersBaseDAO.getUsers().put(user.getUsername(), user);
        usersBaseDAO.writeUsersToXml();
    }

}
