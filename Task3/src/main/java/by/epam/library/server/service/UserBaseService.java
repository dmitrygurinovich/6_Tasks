package by.epam.library.server.service;


import by.epam.library.server.entity.User;
import nu.xom.Document;
import nu.xom.Element;

import java.io.OutputStream;

public interface UserBaseService {
    void format(OutputStream stream, Document doc);
    void addUser(User user);
    Element getXmlElement(User user);
    Document getXmlDocument();
    byte[] encryptUserPassword(String password);
    String decryptUserPassword(String bytesArrayInString);
    byte[] getBytesArrayFromString(String password);
}
