package server;

import client.P2PClient;
import connections.ConnectionThread;
import connections.SocketSelector;
import util.MapWithListener;

import javax.swing.*;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

import static connections.ConnectedSockets.allSockets;

public class P2PServer {

    int port;
//    ServerSocket serverSocket;
    ServerSocketChannel serverSocketChannel;
    public P2PServer(JTextField ipLabel) throws IOException {
        String port_from_env = System.getenv("SERVER_PORT");
        if (port_from_env == null) {
            throw new RuntimeException("SERVER_PORT environment variable is not set");
        }
        port = Integer.parseInt(port_from_env);
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new java.net.InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(SocketSelector.selector, java.nio.channels.SelectionKey.OP_ACCEPT);
        ipLabel.setText("Server is listening on " + Inet4Address.getLocalHost() + ":" + port);
        System.out.println("Server is listening on port " + port);
    }

}
