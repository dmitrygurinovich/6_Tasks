package by.epam.library.server.dao;

import by.epam.library.server.bean.User;
import nu.xom.Document;
import nu.xom.Element;

import java.io.OutputStream;
import java.util.HashMap;

public interface UsersBaseDAO {
    HashMap<String, User> readUsersFromXml();
    void writeUsersToXml();
    HashMap<String, User> getUsers();
    void format(OutputStream stream, Document doc);
    Element getXmlElement(User user);
    byte[] encryptUserPassword(String password);
    String decryptUserPassword(String bytesArrayInString);
    byte[] getBytesArrayFromString(String password);
    Document getXmlDocument();
}
