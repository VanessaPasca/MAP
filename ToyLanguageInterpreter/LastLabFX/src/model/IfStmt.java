package model;

public class IfStmt implements IStmt {
    Exp exp;
    IStmt ifStmt;
    IStmt elseStmt;

    public IfStmt(Exp exp, IStmt ifStmt, IStmt elseStmt) {
        this.exp = exp;
        this.ifStmt = ifStmt;
        this.elseStmt = elseStmt;
    }

    @Override
    public String toString() {
        return "IF "+"("+exp+")"+" THEN "+"("+ifStmt+")"+" ELSE "+"("+elseStmt+")";
    }

    @Override
    public PrgState execute(PrgState state) {
        // if exp then ifStmt else elseStmt
        // Evaluate the expresion ( result 1 means true, 0 means false)
        int val = exp.eval(state.getSymTable(),state.getHeap());
        // Execute the needed statement corresponding to the result of the expr
        if (val!=0) {
            state.getExeStack().push(ifStmt);
        }else{
            state.getExeStack().push(elseStmt);
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(exp.deepCopy(),ifStmt.deepCopy(),elseStmt.deepCopy());
    }
}
