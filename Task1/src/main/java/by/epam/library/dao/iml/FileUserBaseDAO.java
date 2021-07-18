package by.epam.library.dao.iml;

import by.epam.library.bean.Library;
import by.epam.library.bean.User;
import by.epam.library.bean.UserRole;
import by.epam.library.dao.UserBaseDAO;
import by.epam.library.service.ServiceProvider;

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
    private final static ServiceProvider serviceProvider = ServiceProvider.getInstance();
    private final File USERS_BASE_PATH = new File("Task1/src/main/resources/usersbase.txt");

    public FileUserBaseDAO() {
    }

    @Override
    public void writeUserToFile(User user) {

        try (FileWriter writer = new FileWriter(USERS_BASE_PATH, true)) {
            writer.append("---\n");
            writer.append("Id: ").append(String.valueOf(user.getId())).append("\n");
            writer.append("Name: ").append(user.getName()).append("\n");
            writer.append("Login: ").append(user.getLogin()).append("\n");
            writer.append("Password: ").append(Arrays.toString(serviceProvider.getUserPasswordService().encryptUserPassword(user.getPassword()))).append("\n");
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

        users = new ArrayList<>();
        usersListFromFile = new StringBuilder();

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
                        serviceProvider.getUserPasswordService().decryptUserPassword(serviceProvider.getUserPasswordService().getBytesArrayFromString(userFieldsList.get(3))),
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
        boolean isExist;
        Library library = Library.getInstance();

        isExist = false;

        for (User user : library.getUsers()) {
            if (user.getLogin().equals(loginForCheck)) {
                isExist = true;
                break;
            }
        }

        return isExist;
    }
}
