package connections;

import client.P2PClient;
import util.MapWithListener;

import java.net.Socket;
import java.util.HashMap;

public class ConnectedSockets {
    public static MapWithListener<String, Socket> allSockets;
}
