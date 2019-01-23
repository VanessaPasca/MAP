package model;

public class Add extends Exp{
    Exp ex1;
    Exp ex2;

    public Add(Exp ex1, Exp ex2) {
        this.ex1 = ex1;
        this.ex2 = ex2;
    }

    @Override
    int eval(MyIDictionary<String, Integer> table) {
        int v1=ex1.eval(table);
        int v2=ex2.eval(table);
        return (v1+v2)%10;
    }
}
