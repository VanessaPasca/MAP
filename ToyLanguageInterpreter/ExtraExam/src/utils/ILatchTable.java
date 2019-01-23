package utils;

import java.util.Collection;
import java.util.Map;

public interface ILatchTable<T,E> {
    boolean contains(T k);
    E get(T k);
    void add(T k, E v);
    void update(T k, E n);
    Iterable<T> getKeys();
    Iterable<E> getValues();
    Map<T,E> getContent();
    ILatchTable<T,E> copy();
    String toString();
    Collection<E> getCollection();
    public Iterable<Map.Entry<T,E>> getAll();
}
