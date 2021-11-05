package by.epam.library.client.main;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Client instance;
    private static final String HOST = "localhost";
    private static final int PORT = 8089;

    private Client() {}

    public static Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }
        return instance;
    }

    public void startClient() {
        while (true) {
            try (Socket socket = new Socket(HOST, PORT);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true))
            {
                Scanner reader = new Scanner(System.in);
                System.out.println("Enter command for Server:");
                String line = reader.nextLine();
                out.println(line);
                String response = in.readLine();
                System.out.println(response);
            } catch (IOException exception) {
                System.err.println("Server is not available now!\nPlease, start server!");
                break;
            }
        }
    }
}


