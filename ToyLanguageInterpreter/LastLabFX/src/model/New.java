package model;

public class New implements IStmt {
    private String varName;
    private Exp expr;

    public New(String varName, Exp expr) {
        this.varName = varName;
        this.expr = expr;
    }

    @Override
    public PrgState execute(PrgState state) {
        int val=expr.eval(state.getSymTable(),state.getHeap());
        int id= IdHeap.generateID();
        state.getHeap().setValue(id,val);
        state.getSymTable().setVal(varName, id);
        return null;

    }

    @Override
    public String toString() {
        return("new"+"( "+varName+" , "+ expr+" );");
    }

    @Override
    public IStmt deepCopy() {
        return new New(varName,expr.deepCopy());
    }
}
