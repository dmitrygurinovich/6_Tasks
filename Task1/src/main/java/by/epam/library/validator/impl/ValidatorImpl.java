package by.epam.library.validator.impl;

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
        boolean isEmail = false;

        try {
            Pattern emailPattern = Pattern.compile(".*@.*\\.\\w*\\S");
            Matcher matcher = emailPattern.matcher(email);
            isEmail = matcher.matches();
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
        }

        return isEmail;
    }
}
