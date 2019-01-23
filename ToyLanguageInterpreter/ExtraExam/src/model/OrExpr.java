package model;

import utils.MyIDictionary;
import utils.MyIHeap;

public class OrExpr extends Exp {
    Exp ex1,ex2;

    public OrExpr(Exp ex1, Exp ex2) {
        this.ex1 = ex1;
        this.ex2 = ex2;
    }

    @Override
    public String toString() {
        return ex1.toString() + " or " + ex2.toString() + ";";
    }
    @Override
    int eval(MyIDictionary<String, Integer> table, MyIHeap<Integer, Integer> heap) {
        int v1=ex1.eval(table,heap);
        int v2=ex2.eval(table,heap);
        if(v1==1 || v2==1){
            return 1;
        }
        return 0;
    }

    @Override
    Exp deepCopy() {
        return new OrExpr(ex1.deepCopy(),ex2.deepCopy());
    }
}
