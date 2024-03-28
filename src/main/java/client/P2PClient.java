package client;

import connections.ConnectionThread;
import connections.SocketSelector;
import util.MapWithListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.channels.SocketChannel;
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
        try  {
            SocketSelector.clientChannel.connect(new java.net.InetSocketAddress(ip, port));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
