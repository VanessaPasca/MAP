package utils;

import exceptions.CollectionException;

import java.util.HashMap;
import java.util.Map;

public class MyFileTable<K,V> implements MyIFileTable<K,V> {
    public HashMap<K,V> fileMap;

    public MyFileTable() {
        this.fileMap = new HashMap<>();
    }

    @Override
    public void add(K key, V value) {
        fileMap.put(key,value);
    }

    @Override
    public void remove(K key) {
        if(!fileMap.containsKey(key)){
            throw new CollectionException("File "+key+" doesn't exist!");
        }
        fileMap.remove(key);
    }

    @Override
    public V get(K key) {
        if(!fileMap.containsKey(key)){
            throw new CollectionException("File "+key+" doesn't exist!");
        }
        return fileMap.get(key);
    }

    @Override
    public boolean contains(V value) {
        return fileMap.containsValue(value);
    }

    @Override
    public int size() {
        return fileMap.size();
    }

    @Override
    public Iterable<Map.Entry<K, V>> getAll() {
        return fileMap.entrySet();
    }

    @Override
    public String toString() {
        StringBuffer buff=new StringBuffer();
        buff.append("File Table: \n");
        for (K k:this.fileMap.keySet()){
            buff.append("\t\t"+k+ " = "+fileMap.get(k)+"\n");
        }

        return buff.toString();
    }
}
