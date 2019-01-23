package model;

import exceptions.ExpressionException;
import utils.MyIDictionary;
import utils.MyIHeap;

public class ConstExpr extends Exp {
    private int value;

    public ConstExpr(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ""+value;
    }

    @Override
    int eval(MyIDictionary<String, Integer> table, MyIHeap<Integer,Integer> heap) throws ExpressionException {
        /*
        * Returns the value, being a constant
         */
        return value;
    }

    @Override
    Exp deepCopy() {
        return new ConstExpr(value);
    }
}
