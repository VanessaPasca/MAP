package model;

import exceptions.CollectionException;

public class DecrStmt implements IStmt {
    String var;

    public DecrStmt(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return var+"--;";
    }

    @Override
    public PrgState execute(PrgState state) {
        if (!state.getSymTable().has(var)){
            throw new CollectionException(var+" not found in the SymbolTable!");
        }
        int val=state.getSymTable().getVal(var);
        val=val-1;
        state.getSymTable().setVal(var,val);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new DecrStmt(var);
    }
}
