package by.epam.library.service;

import by.epam.library.service.impl.*;

public final class ServiceProvider {
    private static ServiceProvider instance;

    private final EmailSenderService emailSenderService = new EmailSenderServiceImpl();
    private final LibraryService libraryService = new LibraryServiceImpl();
    private final UserBaseService userBaseService = new UserBaseServiceIml();
    private final UserPasswordService userPasswordService = new UserPasswordServiceImpl();
    private final UserService userService = new UserServiceImpl();

    private ServiceProvider() {}

    public static ServiceProvider getInstance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }

    public EmailSenderService getEmailSenderService() {
        return emailSenderService;
    }

    public LibraryService getLibraryService() {
        return libraryService;
    }

    public UserBaseService getUserBaseService() {
        return userBaseService;
    }

    public UserPasswordService getUserPasswordService() {
        return userPasswordService;
    }

    public UserService getUserService() {
        return userService;
    }
}
