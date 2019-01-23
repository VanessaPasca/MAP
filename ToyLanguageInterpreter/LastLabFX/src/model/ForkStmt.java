package model;

import utils.MyIDictionary;
import utils.MyStack;

public class ForkStmt implements IStmt {
    IStmt stmt;

    public ForkStmt(IStmt stmt) {
        this.stmt = stmt;
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIDictionary<String,Integer> dict=state.getSymTable().clone();
        PrgState newOne=new PrgState(
                new MyStack<>(),
                dict,
                state.getOut(),
                state.getFileTable(),
                state.getHeap(),
                stmt,
                state.getId()*10
        );
        return newOne;
    }

    @Override
    public String toString() {
        return ("fork( "+stmt+");");
    }

    @Override
    public IStmt deepCopy() {
        return new ForkStmt(stmt.deepCopy());
    }
}
