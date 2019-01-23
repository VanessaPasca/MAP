package utils;

public interface MyIStack<T> {
    T pop();
    void push(T v);
    boolean isEmpty();
    T top();
    public Iterable<T> getAll();
}
