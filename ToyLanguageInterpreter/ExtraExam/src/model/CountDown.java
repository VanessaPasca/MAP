package model;

import exceptions.CollectionException;

public class CountDown implements IStmt {
    String var;

    public CountDown(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return "countDown("+var+");";
    }
    @Override
    public PrgState execute(PrgState state) {
        if (!state.getSymTable().has(var)){
            throw new CollectionException(var+" not found in SymTable!");
        }
        synchronized (state.getLatchTable()) {
            int index = state.getSymTable().getVal(var);

            if (!state.getLatchTable().contains(index)) {
                throw new CollectionException("index " + index + " not found in the Latch Table!");
            }
            int nr=state.getLatchTable().get(index);
            if (nr>0){
                state.getLatchTable().update(index,nr-1);
                state.getOut().add(state.getId());
            }
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CountDown(var);
    }
}
