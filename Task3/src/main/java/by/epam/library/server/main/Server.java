package by.epam.library.server.main;

import by.epam.library.server.controller.Controller;
import by.epam.library.server.controller.impl.MainController;
import by.epam.library.server.view.View;
import by.epam.library.server.view.impl.ViewImpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8089;
    public static ServerSocket serverSocket;
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
        Controller controller;

        view = ViewImpl.getInstance();
        controller = MainController.getInstance();

        try {
            serverSocket = new ServerSocket(PORT);
            view.print("#Server: server is working...");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out= new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true)
                ) {
                    String line = in.readLine();
                    out.println(controller.action(line));
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

