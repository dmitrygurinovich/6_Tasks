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
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class UsersBaseLogic {
    private final File USERS_BASE_PATH;
    private final SecretKeySpec KEY;

    public UsersBaseLogic() {
        this.USERS_BASE_PATH = new File("Task1/src/main/resources/usersbase.txt");
        this.KEY = new SecretKeySpec("Hdy4rl1dh64MwPfn".getBytes(), "AES");
    }

    public void addUser() {
        User user;
        String email;
        String login;
        View view;
        Library library;

        user = new User();
        view = new View();
        library = Library.getInstance();

        user.setId(library.getUsers().size() + 1);
        user.setName(getStringFromConsole("Enter user's name: "));

        login = getStringFromConsole("Enter user's login: ");
        while(isLoginExist(login)) {
            view.print("Login is exist! Enter new login!");
            login = getStringFromConsole("Enter user's login: ");
        }
        user.setLogin(login);

        user.setPassword(getStringFromConsole("Enter password: "));
        user.setRole((getNumFromConsole("" +
                "Choose user's role:\n" +
                "1. Administrator\n" +
                "2. User",
                0,2) == 1 ? UserRole.ADMINISTRATOR : UserRole.USER));

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
        try (FileWriter writer = new FileWriter(USERS_BASE_PATH, true)) {
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

    public ArrayList<User> readUsersFromFile() {
        ArrayList<User> users;
        StringBuilder usersListFromFile;
        Pattern patternForParsingUserField;
        String[] usersListFromFileDividedBuUser;
        ArrayList<String> userFieldsList;
        Matcher matcher;

        users = new ArrayList<>();
        usersListFromFile = new StringBuilder();
        patternForParsingUserField = Pattern.compile("(?:№\\s*|Id:\\s*|Name:\\s*|Login:\\s*|Password:\\s|Role:\\s|E-mail:\\s)(.*)(?:\\n|$|)");

        try(FileReader reader = new FileReader(USERS_BASE_PATH)) {
            Scanner scanner;

            scanner = new Scanner(reader);

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

                users.add(new User(
                        Integer.parseInt(userFieldsList.get(0)),
                        userFieldsList.get(1),
                        userFieldsList.get(2),
                        decryptUserPassword(getBytesArrayFromString(userFieldsList.get(3))),
                        (userFieldsList.get(4).equals("Administrator") ? UserRole.ADMINISTRATOR : UserRole.USER),
                        userFieldsList.get(5))
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public String decryptUserPassword(byte[] bytes) {
        StringBuilder password;

        password = new StringBuilder();

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, KEY);
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
        View view;
        Scanner in;

        view = new View();
        in = new Scanner(System.in);

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
        View view;
        Scanner in;

        view = new View();
        in = new Scanner(System.in);
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

    public boolean isLoginExist(String loginForCheck) {
        boolean isExist;
        Library library;

        isExist  = false;
        library = Library.getInstance();

        for(User user : library.getUsers()) {
            if (user.getLogin().equals(loginForCheck)) {
                isExist = true;
                break;
            }
        }

        return isExist;
    }

}

