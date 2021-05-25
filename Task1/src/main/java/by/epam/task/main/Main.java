package by.epam.task.main;

import by.epam.task.entity.*;
import by.epam.task.logic.LibraryLogic;
import by.epam.task.logic.UsersBaseLogic;
import by.epam.task.view.View;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;



/*
Задание 1: создать консольное приложение “Учет книг в домашней библиотеке”.
Общие требования к заданию:
• Система учитывает книги как в электронном, так и в бумажном варианте.
• Существующие роли: пользователь, администратор.
• Пользователь может просматривать книги в каталоге книг, осуществлять поиск книг в каталоге.
• Администратор может модифицировать каталог.
• * При добавлении описания книги в каталог оповещение о ней рассылается на e-mail всем пользователям
• ** При просмотре каталога желательно реализовать постраничный просмотр
• * Пользователь может предложить добавить книгу в библиотеку, переслав её администратору на e-mail.
• Каталог книг хранится в текстовом файле.
• Данные аутентификации пользователей хранятся в текстовом файле. Пароль не хранится в открытом виде
 */
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        LibraryLogic logic = new LibraryLogic();
        UsersBaseLogic userLogic = new UsersBaseLogic();
        View view = new View();
        User admin = new User("Ilya", "ilyadmitryevich", "56ewfefw32fe72sd3wO", UserRole.USER, "ilya.gurinovich@tut.by");

        for (User user : userLogic.readUsersFromFile()){
            System.out.println(user);
        }
    }
}
