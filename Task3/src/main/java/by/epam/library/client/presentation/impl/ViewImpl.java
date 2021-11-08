package by.epam.library.client.presentation.impl;

import by.epam.library.client.bean.File;
import by.epam.library.client.presentation.View;

public class ViewImpl implements View {
    @Override
    public void print(File file) {
        System.out.println(file);
    }

    @Override
    public void print(String text) {
        System.out.println(text);
    }
}
