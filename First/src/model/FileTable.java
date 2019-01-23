package model;

import java.util.HashMap;
import java.util.Map;

public class FileTable<K,V> implements MyIDictionary<K,V> {
    private HashMap<K,V> map;

    public FileTable() {
        map=new HashMap<>();
    }

    @Override
    public void setVal(K key, V value) {
        map.put(key,value);
    }

    @Override
    public boolean has(K key) {
        if (map.containsKey(key)){
            return true;
        }
        return false;
    }

    @Override
    public V getVal(K key) {
        if (!map.containsKey(key)){
            throw new RuntimeException();
        }
        return map.get(key);
    }

    @Override
    public Iterable<Map.Entry<K, V>> getAll() {
        return map.entrySet();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void remove(K key) {
        if (!map.containsKey(key)){
            throw new RuntimeException();
        }

        map.remove(key);
    }

    @Override
    public boolean contains(V val) {
        return map.containsValue(val);
    }

    @Override
    public String toString() {
        StringBuffer buff=new StringBuffer();
        buff.append("FileTable: \n");
        for (K key:map.keySet()){
            buff.append("\t\t"+key+"->"+map.get(key)+"\n");
        }
        return buff.toString();
    }
}
