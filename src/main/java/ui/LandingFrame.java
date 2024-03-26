package ui;

import client.P2PClient;
import util.MapListener;
import util.MapWithListener;
import util.RedefinedUI;

import javax.swing.*;
import java.awt.event.ActionListener;

import static connections.ConnectedSockets.allSockets;

public class LandingFrame extends JFrame {

    private JTextField ipLabel;

    public JTextField getIpLabel() {
        return ipLabel;
    }

    private final MapListener showAllConnectedClients = () -> {
        int y = 10;
        JTextField heading = RedefinedUI.goodLabel();
        heading.setText("All Connected Clients");
        heading.setBounds(800, y, 500, 20);
        this.add(heading);
        this.revalidate();
        this.repaint();
        y += 30;
        for (String ip : allSockets.keySet()) {
            JTextField label = RedefinedUI.goodLabel();
            label.setBounds(800, y, 500, 20);
            label.setText(ip);
            this.add(label);
            this.revalidate();
            this.repaint();
            System.out.println(ip);
            y += 30;
        }
    };


    public LandingFrame() {
        super("P2P");
        setSize(1500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        allSockets = new MapWithListener<>(showAllConnectedClients);

        ipLabel = RedefinedUI.goodLabel();
        JTextField connectionField = new JTextField();

        ipLabel.setBounds(10,10, 700, 20);
        connectionField.setBounds(10, 40, 500, 20);
        connectionField.addActionListener(connect);

        this.add(ipLabel);
        this.add(connectionField);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1500,300);
        this.setLayout(null);
        this.setVisible(true);
    }

    private final ActionListener connect = e -> {
        String[] connectionDetails = e.getActionCommand().split(":");
        String ip = connectionDetails[0];
        int port = Integer.parseInt(connectionDetails[1]);
        if(allSockets.containsKey(ip)){
            System.out.println("Already Connected to : " + ip);
            return;
        }
        P2PClient client = new P2PClient(ip, port);
        client.startConnection();
    };



}
