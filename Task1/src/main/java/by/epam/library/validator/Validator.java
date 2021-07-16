package by.epam.library.validator;

public interface Validator {

    boolean isEmail(String email);
    boolean isLoginExist(String loginForCheck);

}
