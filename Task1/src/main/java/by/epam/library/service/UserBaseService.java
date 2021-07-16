package by.epam.library.service;

import by.epam.library.bean.UserRole;

import javax.mail.Address;

public interface UserBaseService {

    Address[] getUsersEmail(UserRole role);
    void addUser();

}
