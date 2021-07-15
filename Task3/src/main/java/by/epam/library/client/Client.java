package by.epam.library.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final String HOST = "localhost";
    public static final int PORT = 8089;
    public static BufferedReader in;
    public static PrintWriter out;
    public static Socket socket;
    public static Scanner reader;

    public static void main(String[] args)  {
        try {
            while (true) {
                try {
                    socket = new Socket(HOST, PORT);
                    reader = new Scanner(System.in);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                    System.out.println("Enter message for Server:");
                    String line = reader.nextLine();
                    System.out.println("Your message: " + line);
                    out.println(line);
                    System.out.println(in.readLine());
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


