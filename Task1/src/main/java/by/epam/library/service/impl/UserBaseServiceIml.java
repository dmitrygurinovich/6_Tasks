package by.epam.library.service.impl;

import by.epam.library.bean.Library;
import by.epam.library.bean.User;
import by.epam.library.bean.UserRole;
import by.epam.library.dao.iml.FileUserBaseDAO;
import by.epam.library.service.UserBaseService;
import by.epam.library.view.impl.DataFromConsoleImpl;
import by.epam.library.view.impl.ViewImpl;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public final class UserBaseServiceIml implements UserBaseService {
    private static UserBaseServiceIml instance;

    private UserBaseServiceIml() {

    }

    public static UserBaseServiceIml getInstance() {
        if (instance == null) {
            instance = new UserBaseServiceIml();
        }
        return instance;
    }

    @Override
    public Address[] getUsersEmail(UserRole role) {
        ArrayList<String> emails;
        Address[] addresses;

        emails = new ArrayList<>();

        for (User user : Library.getInstance().getUsers()) {
            if (user.getRole().equals(role)) {
                emails.add(user.getEmail());
            }
        }

        addresses = new Address[emails.size()];

        try {
            addresses = InternetAddress.parse(emails.toString().substring(1, emails.toString().length() - 1));
        } catch (AddressException exception) {
            exception.printStackTrace();
        }

        return addresses;
    }

    @Override
    public void addUser() {
        User user;
        String email;
        String login;
        ViewImpl view;
        Library library;
        DataFromConsoleImpl dataFromConsole;
        FileUserBaseDAO fileUserBaseDAO;

        user = new User();
        view = ViewImpl.getInstance();
        library = Library.getInstance();
        dataFromConsole = DataFromConsoleImpl.getInstance();
        fileUserBaseDAO = FileUserBaseDAO.getInstance();

        user.setId(library.getUsers().size() + 1);
        user.setName(dataFromConsole.getStringFromConsole("Enter user's name: "));

        login = dataFromConsole.getStringFromConsole("Enter user's login: ");
        while(isLoginExist(login)) {
            view.print("Login is exist! Enter new login!");
            login = dataFromConsole.getStringFromConsole("Enter user's login: ");
        }
        user.setLogin(login);

        user.setPassword(dataFromConsole.getStringFromConsole("Enter password: "));
        user.setRole((dataFromConsole.getNumFromConsole("" +
                        "Choose user's role:\n" +
                        "1. Administrator\n" +
                        "2. User",
                0,2) == 1 ? UserRole.ADMINISTRATOR : UserRole.USER));

        email = dataFromConsole.getStringFromConsole("Enter user's email: ");
        while (!isEmail(email)) {
            email = dataFromConsole.getStringFromConsole("Wrong e-mail format!\nEnter user's email: ");
        }
        user.setEmail(email);

        view.print("User added!");

        library.getUsers().add(user);
        fileUserBaseDAO.writeUserToFile(user);
    }

    @Override
    public boolean isEmail(String email) {
        Pattern emailPattern;
        Matcher matcher;
        boolean isEmail;

        isEmail = false;

        try {
            emailPattern = Pattern.compile(".*@.*\\.\\w*\\S");
            matcher = emailPattern.matcher(email);
            isEmail = matcher.matches();
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
        }

        return isEmail;
    }

    @Override
    public boolean isLoginExist(String loginForCheck) {
        boolean isExist;
        Library library;

        isExist  = false;
        library = Library.getInstance();

        for(User user : library.getUsers()) {
            if (user.getLogin().equals(loginForCheck)) {
                isExist = true;
                break;
            }
        }

        return isExist;
    }
}
