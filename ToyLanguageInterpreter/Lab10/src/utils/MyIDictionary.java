package utils;

import java.util.HashMap;
import java.util.Map;

public interface MyIDictionary<K,V> {
    public V getVal(K key);
    public void setVal(K key, V value);
    public boolean has(K key);
    public int size();
    public MyIDictionary<K,V> clone();

    public Iterable<Map.Entry<K,V>> getAll();
    void setContent(HashMap<K, V> content);
    HashMap<K,V> getContent();
}
