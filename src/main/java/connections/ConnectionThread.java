package connections;

import java.net.Socket;

public class ConnectionThread implements Runnable {

    private Socket clientSocket;

    public ConnectionThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        System.out.println("Connected to : " + clientSocket.getInetAddress().getHostName()+":" + clientSocket.getPort());

    }
}
