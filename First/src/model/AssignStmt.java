package model;

public class AssignStmt implements IStmt {
    private String varName;
    private Exp expr;

    public AssignStmt(String varName, Exp expr) {
        this.varName = varName;
        this.expr = expr;
    }

    @Override
    public PrgState execute(PrgState prg) {
        int val=expr.eval(prg.getDict());
        prg.getDict().setVal(varName,val);
        return prg;
    }
}
