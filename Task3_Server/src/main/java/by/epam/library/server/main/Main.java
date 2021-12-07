package by.epam.library.server.main;

public class Main {
    public static void main(String[] args) {
        Server server = Server.getInstance();
        server.runServer("#Server: server is working...");
    }
}