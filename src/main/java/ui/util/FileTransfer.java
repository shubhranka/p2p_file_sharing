package ui.util;

import java.io.*;
import java.net.Socket;

public class FileTransfer {
    public static void sendFile(String path, Socket socket) {
        new Thread(() -> {
            try {
                DataOutputStream dos = null;
                DataInputStream dis = null;
                dos = new DataOutputStream(socket.getOutputStream());
                File file = new File(path);
                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[4096];
                dos.writeUTF(file.getName());
                dos.writeLong(file.length());
                    while (fis.read(buffer) > 0) {
                    dos.write(buffer);
                }
                fis.close();
                dos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void reciveFile(Socket socket){
    }
}
