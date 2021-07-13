package by.epam.task.server.logic;

import by.epam.task.server.entity.User;
import by.epam.task.server.storage.UsersBase;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Serializer;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

public class UserBaseLogic {
    private static final String USERS_BASE_PATH = "Task3/src/main/resources/users.xml";
    private static final SecretKeySpec KEY = new SecretKeySpec("Hdy2rl1ds64MePhn".getBytes(), "AES");

    public UserBaseLogic() {

    }

    public static void format(OutputStream stream, Document doc) throws Exception {
        Serializer serializer = new Serializer(stream, "ISO-8859-1");
        serializer.setIndent(4);
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
    }

    public void addUser(UsersBase base, User user) throws Exception {
        base.getUsers().put(user.getUsername(), user);
        writeUsersToXml(base);
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

    public Document getXmlDocument(UsersBase base) {
        Element users = new Element("users");

        for (User user : base.getUsers().values()) {
            users.appendChild(getXmlElement(user));
        }

        return new Document(users);
    }

    public void writeUsersToXml(UsersBase base) throws Exception {
        format(new BufferedOutputStream(new FileOutputStream(USERS_BASE_PATH)), getXmlDocument(base));
    }

    public HashMap<String, String> readUsersFromXml() {
        HashMap<String, String> users = new HashMap<>();

        // create new constructor for User class for creating objects from XML

        return users;
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

    public String decryptUserPassword(String bytesArray) {
        StringBuilder password;

        password = new StringBuilder();

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, KEY);
            byte[] chars = cipher.doFinal(getBytesArrayFromString(bytesArray));

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

}
