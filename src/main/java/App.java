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
            P2PServer server = new P2PServer(ipLabel);
            Thread serverThread = new Thread(server);
            serverThread.start();

       }catch (Exception e) {
           e.printStackTrace();
       }
    }



}
