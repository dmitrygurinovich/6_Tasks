package by.epam.library.service;

import by.epam.library.service.impl.*;

public final class ServiceProvider {
    private static ServiceProvider instance;

    private final EmailSenderService EMAIL_SEND_SERVICE = new EmailSenderServiceImpl();
    private final LibraryService LIBRARY_SERVICE = new LibraryServiceImpl();
    private final UserBaseService USER_BASE_SERVICE = new UserBaseServiceIml();
    private final UserPasswordService USER_PASSWORD_SERVICE = new UserPasswordServiceImpl();
    private final UserService USER_SERVICE = new UserServiceImpl();

    private ServiceProvider() {

    }

    public static ServiceProvider getInstance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }

    public EmailSenderService getEmailSenderService() {
        return EMAIL_SEND_SERVICE;
    }

    public LibraryService getLibraryService() {
        return LIBRARY_SERVICE;
    }

    public UserBaseService getUserBaseService() {
        return USER_BASE_SERVICE;
    }

    public UserPasswordService getUserPasswordService() {
        return USER_PASSWORD_SERVICE;
    }

    public UserService getUserService() {
        return USER_SERVICE;
    }
}
