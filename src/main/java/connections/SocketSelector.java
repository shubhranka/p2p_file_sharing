package connections;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class SocketSelector {
    public static Selector selector;
    public static SocketChannel clientChannel;

    static {
        try {
            selector = Selector.open();

            clientChannel = SocketChannel.open();
            clientChannel.configureBlocking(false);
            clientChannel.register(selector, java.nio.channels.SelectionKey.OP_CONNECT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
