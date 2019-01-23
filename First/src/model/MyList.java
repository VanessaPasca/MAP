package model;

import java.util.ArrayList;

public class MyList<T> implements MyIList<T> {
    private ArrayList<T> out;
    public MyList() {
        out=new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuffer buf=new StringBuffer();
        buf.append("Output:\n");
        for (T value: out){
            buf.append("\t\t"+value+"\n");
        }
        return buf.toString();

    }

    @Override
    public void add(T val) {
        out.add(val);
    }

    @Override
    public Iterable<T> getAll() {
        return out;
    }
}
