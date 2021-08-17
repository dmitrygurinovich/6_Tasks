package by.epam.library.server.service.impl;

import by.epam.library.server.bean.User;
import by.epam.library.server.dao.DAOProvider;
import by.epam.library.server.dao.UsersBaseDAO;
import by.epam.library.server.service.UserBaseService;

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
