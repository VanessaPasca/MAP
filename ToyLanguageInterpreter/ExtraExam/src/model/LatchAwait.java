package model;

import exceptions.CollectionException;

public class LatchAwait implements IStmt {
    String var;

    public LatchAwait(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return "await("+var+");";
    }

    @Override
    public PrgState execute(PrgState state) {
        if (!state.getSymTable().has(var)){
            throw new CollectionException(var+" not found in SymTable!");
        }
        int index=state.getSymTable().getVal(var);

        if (!state.getLatchTable().contains(index)){
            throw new CollectionException("index "+index+" not found in the Latch Table!");
        }

        if (state.getLatchTable().get(index)==0){
            //
        }else{
            state.getExeStack().push(this);
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new LatchAwait(var);
    }
}
