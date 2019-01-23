package model;

public class NewLatch implements IStmt {
    private String var;
    private Exp exp;

    public NewLatch(String var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "new latch("+var+", ("+exp+"));";
    }

    @Override
    public PrgState execute(PrgState state) {
        int nr=exp.eval(state.getSymTable(),state.getHeap());
        synchronized (state.getLatchTable()){
            int newId=IdGenerator.generateID();
            state.getLatchTable().add(newId,nr);
            state.getSymTable().setVal(var,newId);
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new NewLatch(var,exp.deepCopy());
    }
}
