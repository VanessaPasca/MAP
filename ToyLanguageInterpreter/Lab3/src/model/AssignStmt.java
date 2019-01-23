package model;

public class AssignStmt implements IStmt {
    private String var;
    private Exp exp;

    public AssignStmt(String var, Exp exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "" + var +":=" + exp +";";
    }

    @Override
    public PrgState execute(PrgState state) {
        //Evaluate the expresion in the assignment
        int val=exp.eval(state.getSymTable());
        //add to the symbol table the name of the variable and the result of the expression
        state.getSymTable().setVal(var,val);
        return state;

    }
}
