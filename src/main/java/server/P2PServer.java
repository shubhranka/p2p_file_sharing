package server;

import client.P2PClient;
import connections.ConnectionThread;
import util.MapWithListener;

import javax.swing.*;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;

import static connections.ConnectedSockets.allSockets;

public class P2PServer implements Runnable {

    int port;
    ServerSocket serverSocket;
    public P2PServer(JTextField ipLabel) throws IOException {
        String port_from_env = System.getenv("SERVER_PORT");
        if (port_from_env == null) {
            throw new RuntimeException("SERVER_PORT environment variable is not set");
        }
        port = Integer.parseInt(port_from_env);
        serverSocket = new ServerSocket(port);
        ipLabel.setText("Server is listening on " + Inet4Address.getLocalHost() + ":" + port);
        System.out.println("Server is listening on port " + port);
    }

     @Override
     public void run() {

         try {
            while (true) {
                Socket socket = serverSocket.accept();
                String ip = socket.getInetAddress().getHostName();
                if(allSockets.containsKey(ip)){
                    System.out.println("Already Connected to IP");
                    System.out.println("Closing Socket");
                    socket.close();
                    continue;
                }
                allSockets.put(ip,socket);
                new Thread(new ConnectionThread(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
