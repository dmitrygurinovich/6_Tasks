package by.epam.library.dao.iml;

import by.epam.library.bean.Library;
import by.epam.library.bean.User;
import by.epam.library.bean.UserRole;
import by.epam.library.dao.UserBaseDAO;
import by.epam.library.service.ServiceProvider;
import by.epam.library.service.UserPasswordService;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class FileUserBaseDAO implements UserBaseDAO {
    private final static File USERS_BASE_PATH = new File("Task1/resources/usersbase.txt"); //TODO сделать относительный путь

    @Override
    public void writeUserToFile(User user) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserPasswordService userPasswordService = serviceProvider.getUserPasswordService();
        byte[] passwordBytesArray = userPasswordService.encryptUserPassword(user.getPassword());
        String password = Arrays.toString(passwordBytesArray);

        try (FileWriter writer = new FileWriter(USERS_BASE_PATH, true)) {
            writer.append("---\n");
            writer.append("Id: ").append(String.valueOf(user.getId())).append("\n");
            writer.append("Name: ").append(user.getName()).append("\n");
            writer.append("Login: ").append(user.getLogin()).append("\n");
            writer.append("Password: ").append(password).append("\n");
            writer.append("Role: ").append(user.getRole().toString()).append("\n");
            writer.append("E-mail: ").append(user.getEmail()).append("\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> readUsersFromFile() {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserPasswordService userPasswordService = serviceProvider.getUserPasswordService();
        ArrayList<User> users = new ArrayList<>();
        StringBuilder usersListFromFile = new StringBuilder();
        Pattern patternForParsingUserField = Pattern
                .compile("(?:№\\s*|Id:\\s*|Name:\\s*|Login:\\s*|Password:\\s|Role:\\s|E-mail:\\s)(.*)(?:\\n|$|)");

        try (FileReader reader = new FileReader(USERS_BASE_PATH)) {
            Scanner scanner = new Scanner(reader);

            while (scanner.hasNextLine()) {
                usersListFromFile.append(scanner.nextLine());
                usersListFromFile.append("\n");
            }

            String[] usersListDividedBuUser = usersListFromFile.toString().split("--\n");

            for (String user : usersListDividedBuUser) {
                ArrayList<String> userFieldsList = new ArrayList<>();
                Matcher matcher = patternForParsingUserField.matcher(user);

                while (matcher.find()) {
                    userFieldsList.add(matcher.group(1));
                }

                byte[] passwordBytesArray = userPasswordService.getBytesArrayFromString(userFieldsList.get(3));
                String password = userPasswordService.decryptUserPassword(passwordBytesArray);

                users.add(new User(
                        Integer.parseInt(userFieldsList.get(0)),
                        userFieldsList.get(1),
                        userFieldsList.get(2),
                        password,
                        (userFieldsList.get(4).equals("Administrator") ? UserRole.ADMINISTRATOR : UserRole.USER),
                        userFieldsList.get(5))
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean isLoginExist(String loginForCheck) {
        Library library = Library.getInstance();

        for (User user : library.getUsers()) {
            if (user.getLogin().equals(loginForCheck)) {
                return true;
            }
        }

        return false;
    }
}
