package client;

import connections.ConnectionThread;
import util.MapWithListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import static connections.ConnectedSockets.allSockets;

public class P2PClient {
    final private int port;
    final private String ip;

    private ConnectionThread connectionThread;
    public P2PClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void startConnection() {
        try(Socket clientSocket = new Socket(ip, port)) {
            connectionThread = new ConnectionThread(clientSocket);
            connectionThread.run();
            allSockets.put(ip, clientSocket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
