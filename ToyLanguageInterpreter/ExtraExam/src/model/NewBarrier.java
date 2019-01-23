package model;

import java.util.ArrayList;

public class NewBarrier implements IStmt {
    String var;
    Exp exp;

    public NewBarrier(String var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "newBarrier("+var+","+exp+");";
    }

    @Override
    public PrgState execute(PrgState state) {
        int val=exp.eval(state.getSymTable(),state.getHeap());

        synchronized (state.getCyclicBarrier()){
            int newLocation=IdGenerator.generateID();
            state.getCyclicBarrier().add(newLocation,new BarrierPair<>(new ArrayList<Integer>(),val));
            state.getSymTable().setVal(var,newLocation);
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NewBarrier(var,exp.deepCopy());
    }
}
