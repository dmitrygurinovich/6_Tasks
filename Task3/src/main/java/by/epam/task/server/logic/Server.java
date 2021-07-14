package by.epam.task.server.logic;

import by.epam.task.server.entity.File;
import by.epam.task.server.storage.FilesBase;
import by.epam.task.server.view.View;

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

    private Server() {

    }

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    public void runServer() {
        View view;

        view = new View();

        try {
            serverSocket = new ServerSocket(PORT);
            view.print("Server is working...");
            while (true) {
                try {
                    socket = serverSocket.accept();
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    String line = in.readLine();
                    if (line.equals("showFiles")) {
                        for (File file : FilesBase.getInstance().getFiles()) {
                            System.out.println(file);
                        }
                    }
                    out.println("Your message \"" + line + "\" delivered");
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

