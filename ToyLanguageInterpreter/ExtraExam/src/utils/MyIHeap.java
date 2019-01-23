package utils;


import java.util.HashMap;
import java.util.Map;

public interface MyIHeap<K,V> {
    public HashMap<K, V> getAsMap();
    V getVal(K key);
    void setValue(K key, V value);
    boolean has(K key);
    int size();
    MyIHeap<K,V> clone();
    Iterable<Map.Entry<K,V>> getAll();
    void setContent(HashMap<K, V> content);
    HashMap<K,V> getContent();
    void setFromMap(Map<K, V> map);

}
