package model;

public class LT extends Exp {
    Exp expr1;
    Exp expr2;

    public LT(Exp expr2, Exp expr21) {
        this.expr1 = expr2;
        this.expr2 = expr21;
    }

    @Override
    int eval(MyIDictionary<String, Integer> table) {
        int v1=expr1.eval(table);
        int v2=expr2.eval(table);
        if (v1<v2){
            return 1;
        }else{
            return 0;
        }
    }
}
