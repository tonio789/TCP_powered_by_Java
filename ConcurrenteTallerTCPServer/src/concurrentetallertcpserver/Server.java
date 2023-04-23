package concurrentetallertcpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    // inicio server socket + lista de clientes + service
    private ServerSocket serverSocket;
    private List<ServerClient> clientList;
    private ExecutorService service;

    // inicializo server
    public Server() {
        try {
            clientList = new ArrayList<>();
            service = Executors.newCachedThreadPool();
            serverSocket = new ServerSocket(7123);
            accept();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // acepto nuevos clientes
    private void accept() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                ServerClient client = new ServerClient(socket);
                clientList.add(client);
                service.submit(client);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
