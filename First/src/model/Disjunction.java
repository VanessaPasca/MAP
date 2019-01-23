package model;

public class Disjunction extends Exp {
    Exp ex1;
    Exp ex2;

    public Disjunction(Exp ex1, Exp ex2) {
        this.ex1 = ex1;
        this.ex2 = ex2;
    }

    @Override
    int eval(MyIDictionary<String, Integer> table) {
        int val1=ex1.eval(table);
        int val2=ex2.eval(table);
        boolean v1,v2;
        switch(val1) {
            case 0: {
                v1 = false;
                break;
            }
            case 1:{
                v1=true; break;
        }
        default: v1=false;
        }

        switch(val2) {
            case 0: {
                v2 = false;
                break;
            }
            case 1:{
                v2=true; break;
            }
            default: v2=false;
        }
        boolean rez= v1||v2;
        if (rez){
            return 1;

        }else{
            return 0;
        }
    }
}
