package by.epam.archive.server.main;

import by.epam.archive.server.controller.ServerController;
import by.epam.archive.server.controller.impl.MainController;
import by.epam.archive.server.view.View;
import by.epam.archive.server.view.impl.ViewImpl;

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

    public void runServer(String message) {
        View view;
        ServerController controller;

        view = ViewImpl.getInstance();
        controller = MainController.getInstance();

        try {
            serverSocket = new ServerSocket(PORT);
            view.print(message);
            String request;
            String response;

            //noinspection InfiniteLoopStatement
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out= new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true)
                ) {

                    request = in.readLine();
                    response = controller.action(request);
                    out.println(response);

                } catch (IOException exception) {

                    serverSocket.close();
                    this.runServer("\n#Server: client has been disconnected!\nWaiting a new connection.");

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}