package model;

import exceptions.CollectionException;

public class IncrStmt implements IStmt {
    String var;

    public IncrStmt(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return var+"++;";
    }

    @Override
    public PrgState execute(PrgState state) {
        if (!state.getSymTable().has(var)){
            throw new CollectionException(var+ " not found in the Symbol Table");
        }

        int val=state.getSymTable().getVal(var);
        val=val+1;
        state.getSymTable().setVal(var,val);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new IncrStmt(var);
    }
}
