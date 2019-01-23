package model;

import exceptions.CollectionException;

import java.util.HashMap;

public class MyDictionary<K,V> implements MyIDictionary<K,V> {
    public HashMap<K,V> map;
    public MyDictionary(){
        map=new HashMap<>();
    }
    @Override
    public V getVal(K key) {
        if (!map.containsKey(key)){
            throw new CollectionException("No element with key "+key);
        }
        return map.get(key);
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
    public int size() {
        return map.size();
    }

    @Override
    public MyIDictionary<K, V> clone() {
        MyIDictionary<K,V> copy=new MyDictionary<>();
        for (K key: map.keySet()){
            V value=map.get(key);
            copy.setVal(key,value);
        }
        return copy;
    }

    @Override
    public String toString() {
        StringBuffer buff=new StringBuffer();
        buff.append("\t Symbol Table: \n");
        for (K key:map.keySet()){
            buff.append("\t\t"+key+" = "+map.get(key)+"\n");
        }
        return buff.toString();
    }
}
