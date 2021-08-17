package by.epam.library.server.dao.impl;

import by.epam.library.server.bean.User;
import by.epam.library.server.bean.UserRole;
import by.epam.library.server.dao.DAOProvider;
import by.epam.library.server.dao.UsersBaseDAO;
import nu.xom.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

public class XMLUsersBaseDAO implements UsersBaseDAO {
    private static final String USERS_BASE_PATH = "Task3/src/main/resources/users.xml";
    private static final SecretKeySpec KEY = new SecretKeySpec("Hdy2rl1ds64MePhn".getBytes(), "AES");
    private final HashMap<String, User> users;

    public XMLUsersBaseDAO(){
        this.users = readUsersFromXml();
    }

    @Override
    public HashMap<String, User> readUsersFromXml() {
        HashMap<String, User> users;
        String username;
        String password;
        UserRole role;

        users = new HashMap<>();

        try {
            Document document = new Builder()
                    .build(USERS_BASE_PATH);

            Elements elements = document.getRootElement().getChildElements();

            for (Element element : elements) {

                username = element.getFirstChildElement("username").getValue();
                password = decryptUserPassword(element.getFirstChildElement("password").getValue());
                if (element.getFirstChildElement("user-role").getValue().equals("Admin")) {
                    role = UserRole.ADMINISTRATOR;
                } else {
                    role = UserRole.USER;
                }

                users.put(username, new User(username, password, role));
            }
        } catch (ParsingException | IOException exception) {
            exception.printStackTrace();
        }

        return users;
    }

    @Override
    public void writeUsersToXml() {
        try {
            format(new BufferedOutputStream(new FileOutputStream(USERS_BASE_PATH)), getXmlDocument());
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

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

    public Document getXmlDocument() {
        Element users;
        UsersBaseDAO usersBaseDAO;

        users = new Element("users");
        usersBaseDAO = DAOProvider.getInstance().getUsersBaseDAO();

        for (User user : usersBaseDAO.getUsers().values()) {
            users.appendChild(getXmlElement(user));
        }

        return new Document(users);
    }

    public HashMap<String, User> getUsers() {
        return users;
    }
}
