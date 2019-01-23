package model;

import exceptions.CollectionException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class MyStack<T> implements MyIStack<T> {
    private Deque<T> st;
    public MyStack() {
        st=new ArrayDeque<>();
    }
    @Override
    public T pop() {
       if (st.isEmpty()){
           throw new CollectionException("Stack is emply!");
       }
       return st.pop();
    }

    @Override
    public void push(T v) {
        st.push(v);

    }

    @Override
    public boolean isEmpty() {
        return st.size()==0;
    }

    @Override
    public T top() {
       if (st.isEmpty()){
           throw new CollectionException("Stack is empty!");
       }
       return st.peek();
    }

    @Override
    public String toString() {
        ArrayList<T> sir=new ArrayList<T>(st);
        StringBuffer buff = new StringBuffer();
        buff.append("Stack: \n");
        for (T elem:sir) {
            buff.append("\t\t"+ elem+ "\t\t");
        }
        buff.append("\n");
        return buff.toString();
    }
}
