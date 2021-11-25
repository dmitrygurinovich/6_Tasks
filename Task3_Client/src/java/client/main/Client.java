package client.main;

import client.controller.ClientController;
import client.controller.impl.MainController;
import client.presentation.PresentationProvider;
import client.presentation.UserInterface;
import client.presentation.View;

import java.io.*;
import java.net.Socket;

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
        View view;
        UserInterface userInterface;
        ClientController controller;

        view = PresentationProvider.getInstance().getVIEW();
        userInterface = PresentationProvider.getInstance().getUSER_INTERFACE();
        controller = new MainController();

        while (true) {
            try (Socket socket = new Socket(HOST, PORT);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true))
            {
                String request = userInterface.adminMenu();
                out.println(request);
                String response = in.readLine();
                controller.action(response);
                //view.print(response);
            } catch (IOException exception) {
                view.print("Server is not available now!\nPlease, start server!");
                break;
            }
        }
    }
}


