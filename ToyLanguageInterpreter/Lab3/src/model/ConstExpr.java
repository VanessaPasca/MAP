package model;

import exceptions.ExpressionException;

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
    int eval(MyIDictionary<String, Integer> table) throws ExpressionException {
        /*
        * Returns the value, being a constant
         */
        return value;
    }
}
