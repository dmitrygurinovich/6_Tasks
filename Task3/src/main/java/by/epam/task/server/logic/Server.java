package by.epam.task.server.logic;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8089;
    public static ServerSocket serverSocket;
    public static Socket socket;
    public static BufferedReader in;
    public static PrintWriter out;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server is working...");
            while (true) {
                try {
                    socket = serverSocket.accept();
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    String line = in.readLine();
                    System.out.println("Message from client: " + line);
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
