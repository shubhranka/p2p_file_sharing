package connections;

import java.io.IOException;
import java.nio.channels.Selector;

public class SocketSelector {
    public static Selector selector;

    static {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
