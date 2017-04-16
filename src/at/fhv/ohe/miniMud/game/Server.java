package at.fhv.ohe.miniMud.game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Oliver H on 16.04.2017.
 */
public class Server extends Thread {
    private ServerSocket _socket;

    public Server(int port) {
        try {
            _socket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.start();
    }

    public static void main(String[] args) {
        new Server(6262);
    }

    public static synchronized void serverLog(String s) {
        System.out.println(s);
    }

    @Override
    public void run() {
        Server.serverLog("<log>Starting Server @" + _socket.getLocalPort());
        while (true) {
            try {
                Socket clientSocket = _socket.accept();
                new ConnectionHandler(clientSocket);

            } catch (IOException e) {
                serverLog(e.getMessage());
                break;
            }
        }
    }
}
