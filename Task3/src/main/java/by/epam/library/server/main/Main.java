package by.epam.library.server.main;

import by.epam.library.server.logic.Server;

public class Main {
    public static void main(String[] args) {
        Server.getInstance().runServer();
    }
}
