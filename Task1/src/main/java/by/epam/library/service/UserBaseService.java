package by.epam.library.service;

import by.epam.library.bean.UserRole;

import javax.mail.Address;

public interface UserBaseService {

    Address[] getUsersEmails(UserRole role);
    void addUser();

}
