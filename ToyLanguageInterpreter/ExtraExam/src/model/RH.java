package model;

import utils.MyIDictionary;
import utils.MyIHeap;

public class RH extends Exp {
    private String varName;
    public RH(String varName) {
        this.varName = varName;
    }

    @Override
    int eval(MyIDictionary<String, Integer> table, MyIHeap<Integer, Integer> heap) {

        int tableVal=table.getVal(varName);
        int heapVal=heap.getVal(tableVal);
        return heapVal;
    }

    @Override
    Exp deepCopy() {
        return new RH(varName);
    }

    @Override
    public String toString() {
        return ("rh( "+varName+" );");
    }
}
