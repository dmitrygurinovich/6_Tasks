package by.epam.archive.client.main;

import by.epam.archive.client.controller.ClientController;
import by.epam.archive.client.controller.impl.MainController;
import by.epam.archive.client.presentation.PresentationProvider;
import by.epam.archive.client.presentation.View;
import by.epam.archive.client.bean.ClientUserSession;

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
                String response;

                if (clientUserSession.getFiles().size() == 0) {
                    request = ("service&get_all_files&server_req");
                } else {
                    if (!clientUserSession.isAuthorized()) {
                        request = controller.action("authorization&authorization");
                    } else {
                        request = controller.action("menu");
                    }
                }
                if (isToServerRequest(request)) {
                    out.println(request);
                    response = in.readLine();
                    controller.action(response);
                } else {
                    out.println("no_req");
                    controller.action(request);
                }
            } catch (IOException exception) {
                view.print("Server is not available now!\nPlease, start server!");
                break;
            }
        }
    }

    private boolean isToServerRequest(String request) {
        String[] params;
        params = request.split("&");

        for(String param : params) {
            if (param.equals("server_req")) {
                return true;
            }
        }
        return false;
    }
}