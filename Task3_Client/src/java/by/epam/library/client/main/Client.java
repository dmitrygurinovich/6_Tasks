package by.epam.library.client.main;

import by.epam.library.client.bean.ClientUserSession;
import by.epam.library.client.controller.ClientController;
import by.epam.library.client.controller.impl.MainController;
import by.epam.library.client.presentation.PresentationProvider;
import by.epam.library.client.presentation.View;

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
        ClientController controller;
        ClientUserSession clientUserSession;

        view = PresentationProvider.getInstance().getVIEW();
        controller = new MainController();
        clientUserSession = ClientUserSession.getInstance();

        while (true) {
            try (Socket socket = new Socket(HOST, PORT);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true))
            {
                String request;

                if (clientUserSession.getFiles().size() == 0) {
                    //get all files after client starting
                    request = ("service&get_all_files");

                } else {

                    if (!clientUserSession.isAuthorized()) {
                        request = controller.action("authorization&authorization");
                    } else {
                        request = controller.action("menu");
                    }

                }

                out.println(request);

                String response = in.readLine();

                controller.action(response);

            } catch (IOException exception) {
                view.print("Server is not available now!\nPlease, start server!");
                break;
            }
        }
    }
}