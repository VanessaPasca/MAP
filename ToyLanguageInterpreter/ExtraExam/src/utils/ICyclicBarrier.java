package utils;

import java.util.Collection;
import java.util.Map;

public interface ICyclicBarrier<T,E> {
    boolean contains(T k);
    E get(T k);
    void add(T k, E v);
    void update(T k, E n);

    Iterable<E> getValues();
    Map<T,E> getContent();
    ICyclicBarrier<T,E> copy();
    String toString();
    Collection<E> getCollection();
    Iterable<Map.Entry<T,E>> getAll();
}
