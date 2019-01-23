package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class MyStack<T> implements MyIStack<T> {
    private Deque<T> st;

    public MyStack() {
        this.st = new ArrayDeque<>();
    }

    @Override
    public void push(T val) {
        st.push(val);
    }

    @Override
    public T pop() {
       if (st.isEmpty()){
           throw new RuntimeException();
       }
       return st.pop();
    }

    @Override
    public Iterable<T> getAll() {
        return st;
    }

    @Override
    public int size() {
        return st.size();
    }

    @Override
    public T top() {
        if (st.isEmpty()){
            throw new RuntimeException();
        }
        return st.peek();
    }

    @Override
    public String toString() {
        ArrayList<T> ar=new ArrayList<>(st);
        StringBuffer buf=new StringBuffer();
        buf.append("Stack: \n");
        for (T el:ar){
            buf.append("\t\t"+el+"\n");
        }
        return buf.toString();
    }
}
