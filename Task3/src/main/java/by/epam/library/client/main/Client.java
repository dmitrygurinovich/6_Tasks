package by.epam.library.client.main;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static Client instance;
    private static final String HOST = "localhost";
    private static final int PORT = 8089;
    private static BufferedReader in;
    private static PrintWriter out;
    private static Socket socket;

    private Client() {}

    public static Client getInstance() {
        if (instance == null) {
            instance = new Client();
        }
        return instance;
    }

    public void startClient() {
        try {
            while (true) {
                try {
                    socket = new Socket(HOST, PORT);
                    Scanner reader = new Scanner(System.in);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    System.out.println("Enter command for Server:");
                    String line = reader.nextLine();
                    System.out.println("Your message: " + line);
                    out.println(line);

                    String response = in.readLine();

                    System.out.println(response);
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


