package model;


import exceptions.InterpreterException;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String,Integer> symTable;
    MyIList<Integer> out;
    IStmt originalProgram;

    public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String, Integer> symTable, MyIList<Integer> out,IStmt prg) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        originalProgram=prg;
        this.exeStack.push(prg);
    }

    public boolean isComplete(){
        return exeStack.isEmpty();
    }


    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer();
        buff.append("Program State : \n");
        buff.append(out.toString());
        buff.append(symTable.toString());
        buff.append(exeStack.toString());
        return buff.toString();
    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public MyIDictionary<String, Integer> getSymTable() {
        return symTable;
    }

    public MyIList<Integer> getOut() {
        return out;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(MyIDictionary<String, Integer> symTable) {
        this.symTable = symTable;
    }

    public void setOut(MyIList<Integer> out) {
        this.out = out;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }
}
