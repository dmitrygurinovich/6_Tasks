package by.epam.task.server.main;

import by.epam.task.server.entity.User;
import by.epam.task.server.logic.FilesBaseLogic;
import by.epam.task.server.logic.UserBaseLogic;
import by.epam.task.server.storage.UsersBase;
import by.epam.task.server.view.View;
import nu.xom.ParsingException;

import java.io.IOException;

public class Main {
    public static void main(String[] args){
        FilesBaseLogic logic = new FilesBaseLogic();
        View view = new View();
        UserBaseLogic userBaseLogic = new UserBaseLogic();
    }
}
