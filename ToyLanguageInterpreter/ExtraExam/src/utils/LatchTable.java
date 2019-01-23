package utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class LatchTable<T,E> implements ILatchTable<T,E> {
    private Map<T,E> map=new HashMap<>();
    public boolean contains(T k){
        return map.containsKey(k);
    }
    public void add(T k, E v){
        map.put(k, v);
    }
    public void update(T k, E n){
        map.put(k, n);
    }
    public E get(T k){
        return map.get(k);
    }
    public Iterable<T> getKeys(){
        return map.keySet();
    }
    public Iterable<E> getValues(){
        return map.values();
    }
    public Map<T,E> getContent(){
        return map;
    }
    @Override
    public ILatchTable<T,E> copy(){
        LatchTable<T,E> dict = new LatchTable<>();
        for(Map.Entry<T,E> v: map.entrySet()){
            dict.add(v.getKey(), v.getValue());
        }
        return dict;
    }

    @Override
    public Iterable<Map.Entry<T, E>> getAll() {
        return this.map.entrySet();
    }


    public Collection<E> getCollection() {
        return map.values();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("LatchTable = {");

        if (!map.isEmpty()) string.append("\n");

        for (T key : map.keySet()) {
            string.append("   " + key + " - " + map.get(key) + "\n");
        }

        string.append("}");
        return string.toString();
    }
}
