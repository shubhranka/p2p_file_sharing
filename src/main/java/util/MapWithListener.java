package util;

import java.util.HashMap;

public class MapWithListener<K,T> extends HashMap<K,T> {
    private final MapListener listener;

    public MapWithListener(MapListener listener) {
        this.listener = listener;
    }

    @Override
    public T put(K key, T value) {
        T result = super.put(key, value);
        listener.onChange();
        return result;
    }

    @Override
    public T remove(Object key) {
        T result = super.remove(key);
        listener.onChange();
        return result;
    }
}
