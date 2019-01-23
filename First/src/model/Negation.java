package model;

public class Negation extends Exp{
    Exp ex;

    public Negation(Exp ex) {
        this.ex = ex;
    }

    @Override
    int eval(MyIDictionary<String, Integer> table) {
        int val=ex.eval(table);
        switch (val) {
            case 0: return 1;
            case 1: return 0;
            default: return 0;
        }
        }
    }

