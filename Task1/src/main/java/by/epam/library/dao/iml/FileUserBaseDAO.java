package by.epam.library.dao.iml;

import by.epam.library.bean.User;
import by.epam.library.bean.UserRole;
import by.epam.library.dao.UserBaseDAO;
import by.epam.library.service.impl.UserPasswordServiceImpl;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUserBaseDAO implements UserBaseDAO {
    private final File USERS_BASE_PATH = new File("Task1/src/main/resources/usersbase.txt");
    private static FileUserBaseDAO instance;

    private FileUserBaseDAO() {

    }

    public static FileUserBaseDAO getInstance() {
        if(instance == null) {
            instance = new FileUserBaseDAO();
        }
        return instance;
    }

    @Override
    public void writeUserToFile(User user) {
        UserPasswordServiceImpl passwordService;
        passwordService = UserPasswordServiceImpl.getInstance();

        try (FileWriter writer = new FileWriter(USERS_BASE_PATH, true)) {
            writer.append("---\n");
            writer.append("Id: ").append(String.valueOf(user.getId())).append("\n");
            writer.append("Name: ").append(user.getName()).append("\n");
            writer.append("Login: ").append(user.getLogin()).append("\n");
            writer.append("Password: ").append(Arrays.toString(passwordService.encryptUserPassword(user.getPassword()))).append("\n");
            writer.append("Role: ").append(user.getRole().toString()).append("\n");
            writer.append("E-mail: ").append(user.getEmail()).append("\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> readUsersFromFile() {
        ArrayList<User> users;
        StringBuilder usersListFromFile;
        Pattern patternForParsingUserField;
        String[] usersListFromFileDividedBuUser;
        ArrayList<String> userFieldsList;
        Matcher matcher;
        UserPasswordServiceImpl passwordService;

        users = new ArrayList<>();
        usersListFromFile = new StringBuilder();
        passwordService = UserPasswordServiceImpl.getInstance();
        patternForParsingUserField = Pattern.compile("(?:№\\s*|Id:\\s*|Name:\\s*|Login:\\s*|Password:\\s|Role:\\s|E-mail:\\s)(.*)(?:\\n|$|)");

        try (FileReader reader = new FileReader(USERS_BASE_PATH)) {
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
                        passwordService.decryptUserPassword(passwordService.getBytesArrayFromString(userFieldsList.get(3))),
                        (userFieldsList.get(4).equals("Administrator") ? UserRole.ADMINISTRATOR : UserRole.USER),
                        userFieldsList.get(5))
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }
}
