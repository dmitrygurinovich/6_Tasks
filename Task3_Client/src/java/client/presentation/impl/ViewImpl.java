package client.presentation.impl;

import client.bean.File;
import client.presentation.View;

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
