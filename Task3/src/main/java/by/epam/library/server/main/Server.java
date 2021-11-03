package by.epam.library.server.main;

import by.epam.library.server.controller.Controller;
import by.epam.library.server.controller.impl.MainController;
import by.epam.library.server.view.View;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8089;
    public static ServerSocket serverSocket;
    public static Socket socket;
    public static BufferedReader in;
    public static PrintWriter out;
    public static Server instance;

    private Server() {}

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    public void runServer() {
        View view;
        Controller controller = MainController.getInstance();

        view = new View();

        try {
            serverSocket = new ServerSocket(PORT);
            view.print("#Server: server is working...");

            while (true) {
                try {
                    socket = serverSocket.accept();
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                    String line = in.readLine();

                    out.println(controller.action(line));
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                } finally {
                    socket.close();
                    in.close();
                    out.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

