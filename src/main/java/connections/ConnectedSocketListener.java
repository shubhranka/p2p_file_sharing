package connections;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class ConnectedSocketListener implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                SocketSelector.selector.select();
                Set<SelectionKey> selectedKeys = SocketSelector.selector.selectedKeys();
                for (SelectionKey key : selectedKeys) {
                    if (key.isAcceptable()) {
                        SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                        clientChannel.configureBlocking(false);
                        clientChannel.register(SocketSelector.selector,SelectionKey.OP_READ);

                        // Get the ip
                        String ip = InetAddress.getLocalHost().getHostAddress();
                        System.out.println("Connected to : " + ip);

                        ConnectedSockets.allSockets.put(ip, clientChannel.socket());
                    }
                    if (key.isReadable()) {
                        SocketChannel clientChannel = (SocketChannel) key.channel();

                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int bytesRead = clientChannel.read(buffer);
                        if(bytesRead == -1) {
                            clientChannel.close();
                            continue;
                        }
                        buffer.flip();
                        System.out.println("Received: " + new String(buffer.array()));
                        buffer.clear();
                    }
                    selectedKeys.remove(key);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
