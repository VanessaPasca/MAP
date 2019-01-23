package utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CyclicBarrier<T,E> implements ICyclicBarrier<T,E> {
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
    @Override
    public Iterable<Map.Entry<T, E>> getAll() {
        return this.map.entrySet();
    }
    public Iterable<E> getValues(){
        return map.values();
    }
    public Map<T,E> getContent(){
        return map;
    }
    @Override
    public ICyclicBarrier<T,E> copy(){
        CyclicBarrier<T,E> dict = new CyclicBarrier<>();
        for(Map.Entry<T,E> v: map.entrySet()){
            dict.add(v.getKey(), v.getValue());
        }
        return dict;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<T, E> e : map.entrySet()) {
            sb.append(e.getKey());
            sb.append("-->");
            sb.append(e.getValue());
        }
        return sb.toString();
    }

    public Collection<E> getCollection() {
        return map.values();
    }
}
