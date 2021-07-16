package by.epam.library.validator.impl;

import by.epam.library.bean.Library;
import by.epam.library.bean.User;
import by.epam.library.validator.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public final class ValidatorImpl implements Validator {
    private static ValidatorImpl instance;

    private ValidatorImpl() {

    }

    public static ValidatorImpl getInstance() {
        if (instance == null) {
            instance = new ValidatorImpl();
        }
        return instance;
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
