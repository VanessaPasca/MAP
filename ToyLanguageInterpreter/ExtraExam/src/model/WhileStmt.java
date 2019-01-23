package model;

public class WhileStmt implements IStmt {
    private Exp expr;
    private IStmt stmt;

    public WhileStmt(Exp expr, IStmt stmt) {
        this.expr = expr;
        this.stmt=stmt;
    }

    @Override
    public String toString() {
        return ("while( "+expr+")"+stmt);
    }

    @Override
    public PrgState execute(PrgState state) {
        int val=expr.eval(state.getSymTable(),state.getHeap());
        if (val!=0){
            state.getExeStack().push(this);
            state.getExeStack().push(stmt);

        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(expr.deepCopy(),stmt.deepCopy());
    }
}
