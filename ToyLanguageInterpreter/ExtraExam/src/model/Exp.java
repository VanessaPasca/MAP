package model;


import utils.MyIDictionary;
import utils.MyIHeap;

public abstract class Exp {
    abstract int eval(MyIDictionary<String,Integer> table, MyIHeap<Integer,Integer> heap);
    abstract Exp deepCopy();
}
