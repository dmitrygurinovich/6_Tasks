package by.epam.library.client.presentation;

import by.epam.library.client.bean.File;

public interface View {
    void print(File file);
    void print(String text);
}
