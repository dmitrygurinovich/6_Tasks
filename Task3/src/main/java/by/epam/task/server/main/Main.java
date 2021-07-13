package by.epam.task.server.main;


import by.epam.task.server.logic.UserBaseLogic;
import by.epam.task.server.storage.FilesBase;
import by.epam.task.server.logic.FilesBaseLogic;
import by.epam.task.server.storage.UsersBase;
import by.epam.task.server.view.View;

public class Main {
    public static void main(String[] args){

        FilesBaseLogic logic = new FilesBaseLogic();
        FilesBase base = new FilesBase();
        View view = new View();
        UsersBase users = new UsersBase();
        UserBaseLogic userBaseLogic = new UserBaseLogic();


    }
}
