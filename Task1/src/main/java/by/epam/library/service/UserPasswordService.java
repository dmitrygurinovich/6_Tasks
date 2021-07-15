package by.epam.library.service;

public interface UserPasswordService {

    byte[] getBytesArrayFromString(String password);
    String decryptUserPassword(byte[] bytes);
    byte[] encryptUserPassword(String password);

}
