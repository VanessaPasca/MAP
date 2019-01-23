package model;

public class RepeatStmt implements IStmt {
    IStmt s;
    Exp ex;

    public RepeatStmt(IStmt s, Exp ex) {
        this.s = s;
        this.ex = ex;
    }

    @Override
    public String toString() {
        return "Repeat "+ s.toString() +"until "+ ex.toString()+";";
    }

    @Override
    public PrgState execute(PrgState state) {
        IStmt stmtt=new CompStmt(s,new WhileStmt(new NotExpr(ex),s));
        state.getExeStack().push(stmtt);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new RepeatStmt(s.deepCopy(),ex.deepCopy());
    }
}
