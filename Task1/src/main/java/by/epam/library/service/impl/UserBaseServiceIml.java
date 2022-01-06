package by.epam.library.service.impl;

import by.epam.library.bean.Library;
import by.epam.library.bean.User;
import by.epam.library.bean.UserRole;
import by.epam.library.dao.DAOProvider;
import by.epam.library.dao.UserBaseDAO;
import by.epam.library.presentation.DataFromConsole;
import by.epam.library.presentation.PresentationProvider;
import by.epam.library.presentation.View;
import by.epam.library.service.UserBaseService;
import by.epam.library.validator.impl.ValidatorImpl;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;

public final class UserBaseServiceIml implements UserBaseService {

    public UserBaseServiceIml() {

    }

    @Override
    public Address[] getUsersEmails(UserRole role) {
        ArrayList<String> emails = new ArrayList<>();

        for (User user : Library.getInstance().getUsers()) {
            if (user.getRole().equals(role)) {
                emails.add(user.getEmail());
            }
        }

        Address[] addresses = new Address[emails.size()];

        try {
            addresses = InternetAddress.parse(emails.toString().substring(1, emails.toString().length() - 1));
        } catch (AddressException exception) {
            exception.printStackTrace();
        }

        return addresses;
    }

    @Override
    public void addUser() {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserBaseDAO userBaseDAO = daoProvider.getUserBaseDAO();
        PresentationProvider viewProvider = PresentationProvider.getInstance();
        DataFromConsole dataFromConsole = viewProvider.getDataFromConsole();
        View view = viewProvider.getView();
        ValidatorImpl  validator = ValidatorImpl.getInstance();
        Library library = Library.getInstance();
        ArrayList<User> users = library.getUsers();
        User user = new User();

        user.setId(library.getUsers().size() + 1);
        user.setName(dataFromConsole.getStringFromConsole("Enter user's name: "));

        String login = dataFromConsole.getStringFromConsole("Enter user's login: ");

        while (userBaseDAO.isLoginExist(login)) {
            view.print("Login is exist! Enter new login!");
            login = dataFromConsole.getStringFromConsole("Enter user's login: ");
        }
        user.setLogin(login);

        user.setPassword(dataFromConsole.getStringFromConsole("Enter password: "));
        user.setRole((dataFromConsole.getNumFromConsole("" +
                        "Choose user's role:\n" +
                        "1. Administrator\n" +
                        "2. User",
                0, 2) == 1 ? UserRole.ADMINISTRATOR : UserRole.USER));

        String email = dataFromConsole.getStringFromConsole("Enter user's email: ");

        while (!validator.isEmail(email)) {
            email = dataFromConsole.getStringFromConsole("Wrong e-mail format!\nEnter user's email: ");
        }
        user.setEmail(email);
        view.print("User has been added!");
        users.add(user);
        userBaseDAO.writeUserToFile(user);
    }

}
