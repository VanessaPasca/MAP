package model;

import java.util.ArrayList;

public class MyList<T> implements MyIList<T> {
    private ArrayList<T> out;
    public MyList(){
        out=new ArrayList<>();
    }
    @Override
    public void add(T value) {
        out.add(value);
    }

    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer();
        buff.append(" Output: \n");
        for (T value: out){
            buff.append("\t\t"+value+"\n");
        }
        return buff.toString();
    }
}
