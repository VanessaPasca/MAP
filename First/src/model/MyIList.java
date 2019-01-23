package model;

public interface MyIList<T> {
    void add(T val);
    Iterable<T> getAll();
}
