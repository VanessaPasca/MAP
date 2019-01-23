package model;

public interface MyIStack<T> {
    void push(T val);
    T pop();
    Iterable<T> getAll();
    int size();
    T top();
}
