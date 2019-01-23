package model;

public class WH implements IStmt {
    private String varName;
    private Exp expr;

    public WH(String varName, Exp expr) {
        this.varName = varName;
        this.expr = expr;
    }

    @Override
    public String toString() {
       return("wH( "+varName+" , "+expr+" );");
    }

    @Override
    public PrgState execute(PrgState state) {
        int val=expr.eval(state.getSymTable(),state.getHeap());
        int heapKey=state.getSymTable().getVal(varName);
        state.getHeap().setValue(heapKey,val);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new WH(varName,expr.deepCopy());
    }
}
