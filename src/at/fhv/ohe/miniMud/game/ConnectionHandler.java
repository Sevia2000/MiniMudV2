package at.fhv.ohe.miniMud.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Oliver H on 16.04.2017.
 */
public class ConnectionHandler extends Thread {
    private Socket _clientSocket;
    private BufferedReader _fromClient;
    private PrintStream _toClient;
    private Controller _controller;

    private boolean _isActive;
    private int _id;
    private static int iDs = 0;


    public ConnectionHandler(Socket mySocket) {
        _id = iDs++;
        _clientSocket = mySocket;
        Server.serverLog("<log>new Connection @" + _clientSocket.getInetAddress().getHostAddress());

        try {
            _fromClient = new BufferedReader(new InputStreamReader(_clientSocket.getInputStream()));
            _toClient = new PrintStream(_clientSocket.getOutputStream());
        } catch (IOException e) {
            try {
                _clientSocket.close();
                Server.serverLog("<log>Connection refused ->" + e.getMessage());
            } catch (IOException e1) {
                Server.serverLog("<log>Socket error ->" + e1.getMessage());
            }
        }
        _isActive = true;
        this.start();
    }

    @Override
    public void run() {
        _controller = new Controller(this);
        String getLine;
        try {
            while (_isActive) {
                getLine = _fromClient.readLine();
                if (getLine == null) {
                    _controller.playerLost();
                    _isActive = false;
                    _controller = null;
                } else {
                    _controller.toExecuteQueue(getLine);
                    synchronized (_controller) {
                        _controller.notify();
                    }
                }
            }
        } catch (IOException e) {
            Server.serverLog("<log>ExeptionInRun ->" + e.getMessage());
        }
        try {
            _clientSocket.close();
        } catch (IOException e) {
            Server.serverLog("<log>ExeptionInRun ->" + e.getMessage());
        }
        Server.serverLog("<log>Client disconnect");
    }

    public void logOutPerformed () {
        _isActive = false;
    }

    public void sendToClient(String s) {
        try {
            _toClient.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
