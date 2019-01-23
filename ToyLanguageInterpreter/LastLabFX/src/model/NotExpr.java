package model;

import exceptions.ExpressionException;
import utils.MyIDictionary;
import utils.MyIHeap;

public class NotExpr extends Exp {
    Exp ex;

    public NotExpr(Exp ex) {
        this.ex = ex;
    }

    @Override
    public String toString() {
        return "not "+ex.toString();
    }

    @Override
    int eval(MyIDictionary<String, Integer> table, MyIHeap<Integer, Integer> heap) {
        int val=ex.eval(table,heap);
        if (val==0){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    Exp deepCopy() {
        return new NotExpr(ex.deepCopy());
    }
}
