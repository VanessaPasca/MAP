package model;

import exceptions.ExpressionException;
import utils.MyIDictionary;
import utils.MyIHeap;

public class BoolExpr extends Exp {
    private Exp expr1;
    private Exp expr2;
    private String comp;

    public BoolExpr(Exp expr1, Exp expr2, String comp) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.comp = comp;
    }

    @Override
    public String toString() {
        return ""+expr1+" "+comp+" "+expr2;
    }

    @Override
    int eval(MyIDictionary<String, Integer> table, MyIHeap<Integer,Integer> heap) throws ExpressionException {
        // expr1 comp expr2
        //Evaluate the two expresions
        int v1= expr1.eval(table, heap);
        int v2=expr2.eval(table, heap);
        //Check the comparator and compare the results of the expressions correspondingly
        switch (comp){
            case "<": return (v1<v2) ? 1:0;
            case ">": return (v1>v2) ? 1:0;
            case "<=": return v1<=v2 ? 1:0;
            case ">=": return v1>=v2 ? 1:0;
            case "==": return v1==v2 ? 1:0;
            case "!=": return v1!=v2 ? 1:0;
            default:
                throw new ExpressionException("Invalid comparator!");
            }

        }

    @Override
    Exp deepCopy() {
        return new BoolExpr(expr1.deepCopy(),expr2.deepCopy(),comp);
    }
}

