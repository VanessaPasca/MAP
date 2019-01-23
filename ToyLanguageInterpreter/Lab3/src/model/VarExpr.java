package model;

import exceptions.ExpressionException;

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
    int eval(MyIDictionary<String, Integer> table) throws ExpressionException {
        /*
        * Returns the value mapped to the id
         */
        return table.getVal(id);
    }
}
