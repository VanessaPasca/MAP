package model;

public class BarrierPair<T,E> {
    private T first; private E second;
    public BarrierPair(T t, E e){
        first = t; second = e;
    }


    public E getSecond() {
        return second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(E second) {
        this.second = second;
    }

    public String toString(){
        return " <["+ first.toString()+ "], " + second+">";
    }
}
