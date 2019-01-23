package model;

import exceptions.ExpressionException;
import utils.MyIDictionary;
import utils.MyIHeap;

public class VarExpr extends Exp {
    private String id;

    public VarExpr(String name) {
        this.id = name;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    int eval(MyIDictionary<String, Integer> table, MyIHeap<Integer,Integer> heap) throws ExpressionException {
        /*
        * Returns the value mapped to the id
         */
        return table.getVal(id);
    }

    @Override
    Exp deepCopy() {
        return new VarExpr(id);
    }
}
