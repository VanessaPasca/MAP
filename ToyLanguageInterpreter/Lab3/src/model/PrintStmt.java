package model;

public class PrintStmt implements IStmt {
    Exp exp;

    public PrintStmt(Exp exe) {
        this.exp = exe;
    }

    @Override
    public String toString() {
        return "print(" +exp+")";
    }

    @Override
    public PrgState execute(PrgState state) {
        int val=exp.eval(state.getSymTable());
        state.getOut().add(val);
        return state;

    }
}
