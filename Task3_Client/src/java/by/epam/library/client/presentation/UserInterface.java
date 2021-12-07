package by.epam.library.client.presentation;

import by.epam.library.client.bean.File;

public interface UserInterface {
    String adminMenu();
    String userMenu();
    String editFile();
    File editProgress(File file);
    String searchFiles();
}
