package model;


import exceptions.CollectionException;
import utils.*;

import java.util.List;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String,Integer> symTable;
    MyIList<Integer> out;
    MyIFileTable<Integer,FileInfo> fileTable;
    MyIHeap<Integer, Integer> heap;
    IStmt originalProgram;
    int id;

    ILatchTable<Integer, Integer> latchTable;
    ICyclicBarrier<Integer,BarrierPair<List<Integer>,Integer>> cyclicBarrier;

    public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String, Integer> symTable, MyIList<Integer> out, MyIFileTable<Integer,FileInfo> ft, MyIHeap<Integer,Integer> heap, IStmt prg, int id,ILatchTable<Integer, Integer> latchTable,    ICyclicBarrier<Integer,BarrierPair<List<Integer>,Integer>> cyclicBarrier) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable=ft;
        this.exeStack.push(prg);
        this.originalProgram=prg.deepCopy();
        this.heap=heap;
        this.id=id;
        this.latchTable=latchTable;
        this.cyclicBarrier=cyclicBarrier;
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
        buff.append(latchTable.toString());
        buff.append(cyclicBarrier.toString());

        return buff.toString();
    }
    public boolean isNotCompleted(){
        if (this.exeStack.isEmpty()){
            return false;
        }
        return true;
    }

    public ICyclicBarrier<Integer, BarrierPair<List<Integer>, Integer>> getCyclicBarrier() {
        return cyclicBarrier;
    }

    public ILatchTable<Integer, Integer> getLatchTable() {
        return latchTable;
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

    public void setLatchTable(ILatchTable<Integer, Integer> latchTable) {
        this.latchTable = latchTable;
    }

    public void setCyclicBarrier(ICyclicBarrier<Integer, BarrierPair<List<Integer>, Integer>> cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
