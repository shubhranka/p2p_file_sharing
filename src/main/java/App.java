import connections.ConnectedSocketListener;
import ui.LandingFrame;
import util.RedefinedUI;
import client.P2PClient;
import server.P2PServer;

import javax.swing.*;
import java.awt.event.ActionListener;

import static connections.ConnectedSockets.allSockets;

public class App {

    public static void main(String[] args) {
       try {
            // UI Components
            LandingFrame frame = new LandingFrame();
            JTextField ipLabel = frame.getIpLabel();

            // Server
            new P2PServer(ipLabel);

            // Start Listener
           ConnectedSocketListener connectedSocketListener = new ConnectedSocketListener();
           Thread listenerThread = new Thread(connectedSocketListener);
           listenerThread.start();


       }catch (Exception e) {
           e.printStackTrace();
       }
    }



}
