package model;

public interface MyIDictionary<K,V> {
    public V getVal(K key);
    public void setVal(K key, V value);
    public boolean has(K key);
    public int size();
    public MyIDictionary<K,V> clone();
}
