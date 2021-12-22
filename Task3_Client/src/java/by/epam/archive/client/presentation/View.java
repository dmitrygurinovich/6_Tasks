package by.epam.archive.client.presentation;

import by.epam.archive.client.bean.File;

public interface View {
    void print(File file);
    void print(String text);
}
