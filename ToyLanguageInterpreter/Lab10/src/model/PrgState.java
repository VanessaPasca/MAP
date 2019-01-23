package model;


import exceptions.CollectionException;
import utils.*;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String,Integer> symTable;
    MyIList<Integer> out;
    MyIFileTable<Integer,FileInfo> fileTable;
    MyIHeap<Integer, Integer> heap;
    IStmt originalProgram;
    int id;

    public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String, Integer> symTable, MyIList<Integer> out, MyIFileTable<Integer,FileInfo> ft, MyIHeap<Integer,Integer> heap, IStmt prg,int id) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable=ft;
        this.exeStack.push(prg);
        this.originalProgram=prg.deepCopy();
        this.heap=heap;
        this.id=id;
    }


    public boolean isComplete(){
        return exeStack.isEmpty();
    }

    public PrgState oneStep(){
        if (exeStack.isEmpty()){
            throw new CollectionException("Stack empty!");
        }
        IStmt crtStmt=exeStack.pop();
        return crtStmt.execute(this);
    }
    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer();
        buff.append("Program State for: "+id+"\n");
        buff.append(out.toString());
        buff.append(symTable.toString());
        buff.append(fileTable.toString());
        buff.append(heap.toString());
        buff.append(exeStack.toString());


        return buff.toString();
    }
    public boolean isNotCompleted(){
        if (this.exeStack.isEmpty()){
            return false;
        }
        return true;
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

    public MyIFileTable<Integer, FileInfo> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyIFileTable<Integer, FileInfo> fileTable) {
        this.fileTable = fileTable;
    }

    public MyIHeap<Integer, Integer> getHeap() {
        return heap;
    }

    public void setHeap(MyIHeap<Integer, Integer> heap) {
        this.heap = heap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
