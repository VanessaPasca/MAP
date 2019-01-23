package model;

import java.util.Map;

public interface MyIDictionary<K,V> {
    void setVal(K key,V value);
    boolean has(K key);
    V getVal(K key);

    Iterable<Map.Entry<K,V>> getAll();
    int size();
    void remove(K key);
    boolean contains(V val);
}
