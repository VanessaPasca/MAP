package model;

public class Case{
    Exp cond;
    IStmt dothis;

    public Case(Exp cond, IStmt d) {
        this.cond = cond;
        this.dothis=d;
    }

    @Override
    public String toString() {
        return "case "+cond.toString()+" : "+dothis.toString();
    }
}