package model;

public class ZR extends Exp {
    Exp e;

    public ZR(Exp e) {
        this.e = e;
    }

    @Override
    int eval(MyIDictionary<String, Integer> table) {
        int v=e.eval(table);
        if (v>0){
            return 0;
        }else if (v==0){
            return 1;
        }else{
            return 0;
        }
    }
}
