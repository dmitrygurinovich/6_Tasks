package by.epam.library.server.service.impl;

import by.epam.library.server.dao.DAOProvider;
import by.epam.library.server.dao.UsersBaseDAO;
import by.epam.library.server.entity.User;
import by.epam.library.server.service.UserBaseService;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class UserBaseServiceImpl implements UserBaseService {
    private static final SecretKeySpec KEY = new SecretKeySpec("Hdy2rl1ds64MePhn".getBytes(), "AES");

    @Override
    public void format(OutputStream stream, Document doc) {
        try{
            Serializer serializer = new Serializer(stream, "ISO-8859-1");
            serializer.setIndent(4);
            serializer.setMaxLength(120);
            serializer.write(doc);
            serializer.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        UsersBaseDAO usersBaseDAO;

        usersBaseDAO = DAOProvider.getInstance().getUsersBaseDAO();

        usersBaseDAO.getUsers().put(user.getUsername(), user);
        usersBaseDAO.writeUsersToXml();
    }

    @Override
    public Element getXmlElement(User user) {
        Element userXml = new Element("user");
        Element username = new Element("username");
        Element password = new Element("password");
        Element userRole = new Element("user-role");

        username.appendChild(user.getUsername());
        password.appendChild(Arrays.toString(encryptUserPassword(user.getPassword())));
        userRole.appendChild(user.getRole().toString());

        userXml.appendChild(username);
        userXml.appendChild(password);
        userXml.appendChild(userRole);

        return userXml;
    }

    @Override
    public byte[] encryptUserPassword(String password) {
        byte[] passwordsBytes;

        passwordsBytes = new byte[0];

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, KEY);
            passwordsBytes = cipher.doFinal(password.getBytes());
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | IllegalStateException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return passwordsBytes;
    }

    @Override
    public String decryptUserPassword(String bytesArrayInString) {
        StringBuilder password;

        password = new StringBuilder();

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, KEY);
            byte[] chars = cipher.doFinal(getBytesArrayFromString(bytesArrayInString));

            for (byte b : chars) {
                password.append((char) b);
            }

        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException|
                BadPaddingException e) {
            e.printStackTrace();
        }
        return password.toString();
    }

    @Override
    public byte[] getBytesArrayFromString(String password) {
        String[] passwordParsedToStringsArray;
        byte[] passwordParsedToBytesArray;

        passwordParsedToStringsArray = password.substring(1, password.length() - 1).split(", ");
        passwordParsedToBytesArray = new byte[passwordParsedToStringsArray.length];

        for (int i = 0; i < passwordParsedToStringsArray.length; i++) {
            passwordParsedToBytesArray[i] = Byte.parseByte(passwordParsedToStringsArray[i]);
        }

        return passwordParsedToBytesArray;
    }

    @Override
    public Document getXmlDocument() {
        Element users = new Element("users");
        UsersBaseDAO usersBaseDAO = DAOProvider.getInstance().getUsersBaseDAO();

        for (User user : usersBaseDAO.getUsers().values()) {
            users.appendChild(getXmlElement(user));
        }

        return new Document(users);
    }
}
