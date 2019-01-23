package utils;

import exceptions.CollectionException;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<K,V> implements MyIHeap<K,V> {
    private HashMap<K,V> map;
    public MyHeap(){
        map=new HashMap<>();
    }
    @Override
    public V getVal(K key) {
        if (this.has(key)==false){
            throw new CollectionException("No element with this key in the heap!");
        }
        return map.get(key);
    }

    @Override
    public void setFromMap(Map<K, V> _map)
    {
        map = (HashMap<K, V>)_map;
    }
    @Override
    public HashMap<K, V> getAsMap()
    {
        return map;
    }

    @Override
    public void setValue(K key, V value) {
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
    public MyIHeap<K, V> clone() {
       MyIHeap<K,V> copy=new MyHeap<>();
       for (K key:map.keySet()){
           V value=map.get(key);
           copy.setValue(key,value);
       }
       return copy;
    }

    @Override
    public Iterable<Map.Entry<K, V>> getAll() {
        return map.entrySet();
    }

    @Override
    public void setContent(HashMap<K, V> content) {
        this.map=content;
    }

    @Override
    public HashMap<K, V> getContent() {
        return this.map;
    }


    @Override
    public String toString() {
        StringBuffer buff=new StringBuffer();
        buff.append("\t Heap: \n");
        for (K key:map.keySet()){
            buff.append("\t\t"+key+" = "+map.get(key)+"\n");
        }

        return buff.toString();
    }
}
