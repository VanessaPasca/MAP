package model;

import java.util.ArrayList;

public class SwitchStmt implements IStmt {
    Exp ex;
    ArrayList<Case> cases;
    IStmt def;

    public SwitchStmt(Exp ex, ArrayList<Case> cases,IStmt def) {
        this.ex = ex;
        this.cases = cases;
        this.def=def;
    }

    public SwitchStmt(Exp ex, Case s1,Case s2, IStmt s3){
        this.ex=ex;
        cases=new ArrayList<>();
        cases.add(s1);
        cases.add(s2);
        def=s3;

    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("switch("+ex.toString()+")");
        for (Case c:cases){
            sb.append(c.toString());
        }

        sb.append("default: "+def.toString());
        return sb.toString();
    }

    @Override
    public PrgState execute(PrgState state) {
      if (cases.isEmpty()){
          state.getExeStack().push(def);
          return null;
      }
      IfStmt branch=new IfStmt(new BoolExpr(ex,cases.get(cases.size()-1).cond,"=="),cases.get(cases.size()-1).dothis,def);

      for (int i=cases.size()-2;i>=0;i--){
          branch=new IfStmt(new BoolExpr(ex,cases.get(i).cond,"=="),cases.get(i).dothis,branch);
      }
      state.getExeStack().push(branch);
      return null;
    }

    @Override
    public IStmt deepCopy() {
        return new SwitchStmt(ex.deepCopy(),cases,def.deepCopy());
    }


}
