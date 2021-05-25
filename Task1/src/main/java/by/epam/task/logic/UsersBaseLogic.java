package by.epam.task.logic;

import by.epam.task.entity.Library;
import by.epam.task.entity.User;
import by.epam.task.entity.UserRole;
import by.epam.task.view.View;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class UsersBaseLogic {
    private final File usersBasePath = new File("Task1/src/main/resources/usersbase.txt");
    private final SecretKeySpec key = new SecretKeySpec("Hdy4rl1dh64MwPfn".getBytes(), "AES");
    private final Scanner in = new Scanner(System.in);
    private final View view = new View();

    public UsersBaseLogic() {}

    public void addUser(Library library) {
        User user;
        String email;

        user = new User();

        user.setName(getStringFromConsole("Enter user's name: "));
        // TODO: добавить проверку на существование логина в базе
        user.setLogin(getStringFromConsole("Enter user's login: "));
        user.setPassword(getStringFromConsole("Enter password: "));
        user.setRole((getNumFromConsole("Choose user's role:\n" +
                "1. Administrator\n2. User", 0,2) == 1 ? UserRole.ADMINISTRATOR : UserRole.USER));

        email = getStringFromConsole("Enter user's email: ");
        while (!isEmail(email)) {
            email = getStringFromConsole("Wrong e-mail format!\nEnter user's email: ");
        }
        user.setEmail(email);

        view.print("User added!");

        library.getUsers().add(user);
        writeUserToFile(user);
    }

    public void writeUserToFile(User user) {
        try (FileWriter writer = new FileWriter(usersBasePath, true)) {
            writer.append("---\n");
            writer.append("Id: ").append(String.valueOf(user.getId())).append("\n");
            writer.append("Name: ").append(user.getName()).append("\n");
            writer.append("Login: ").append(user.getLogin()).append("\n");
            writer.append("Password: ").append(Arrays.toString(encryptUserPassword(user.getPassword()))).append("\n");
            writer.append("Role: ").append(user.getRole().toString()).append("\n");
            writer.append("E-mail: ").append(user.getEmail()).append("\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readUsersFromFile() {
        ArrayList<User> users;
        StringBuilder usersListFromFile;
        Pattern patternForParsingUserField;
        String[] usersListFromFileDividedBuUser;
        ArrayList<String> userFieldsList;
        Matcher matcher;

        users = new ArrayList<>();
        usersListFromFile = new StringBuilder();
        patternForParsingUserField = Pattern.compile("(?:№\\s*|Id:\\s*|Name:\\s*|Login:\\s*|Password:\\s|Role:\\s|E-mail:\\s)(.*)(?:\\n|$|)");

        try(FileReader reader = new FileReader(usersBasePath)) {
            Scanner scanner = new Scanner(reader);

            while (scanner.hasNextLine()) {
                usersListFromFile.append(scanner.nextLine());
                usersListFromFile.append("\n");
            }

            usersListFromFileDividedBuUser = usersListFromFile.toString().split("--\n");

            for (String user : usersListFromFileDividedBuUser) {
                userFieldsList = new ArrayList<>();
                matcher = patternForParsingUserField.matcher(user);

                while (matcher.find()) {
                    userFieldsList.add(matcher.group(1));
                }


            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] encryptUserPassword(String password) {
        byte[] passwordsBytes = new byte[0];
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            passwordsBytes = cipher.doFinal(password.getBytes());
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | IllegalStateException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return passwordsBytes;
    }

    public String decryptUserPassword(byte[] bytes) {
        StringBuilder password;
        password = new StringBuilder();
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] chars = cipher.doFinal(bytes);

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

    public int getNumFromConsole(String message, int min, int max) {
        int number;
        view.print(message);
        while(!in.hasNextInt()) {
            view.print(message);
            in.next();
        }
        number = in.nextInt();
        in.nextLine();
        if (number > min && number <= max) {
            return number;
        } else {
            return getNumFromConsole(message, min, max);
        }
    }

    public String getStringFromConsole(String message) {
        String text;
        view.print(message);

        while (!in.hasNextLine()) {
            view.print(message);
            in.next();
        }
        text = in.nextLine();
        return text;
    }

    public boolean isEmail(String email) {
        Pattern emailPattern;
        Matcher matcher;
        boolean isEmail;

        isEmail = false;

        try {
            emailPattern = Pattern.compile(".*@.*\\.\\w*\\S");
            matcher = emailPattern.matcher(email);
            isEmail = matcher.matches();
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
        }

        return isEmail;
    }
}
