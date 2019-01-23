package model;

public class PrgState {
    private MyIStack<IStmt> st;
    private MyIDictionary<String,Integer> dict;
    private MyIList<Integer> out;
    private FileTable<Integer,FileInfo> ft;

    public PrgState(MyIStack<IStmt> st, MyIDictionary<String, Integer> dict, MyIList<Integer> out, FileTable<Integer, FileInfo> ft) {
        this.st = st;
        this.dict = dict;
        this.out = out;
        this.ft = ft;
    }

    public void setSt(MyIStack<IStmt> st) {
        this.st = st;
    }

    public void setDict(MyIDictionary<String, Integer> dict) {
        this.dict = dict;
    }

    public void setOut(MyIList<Integer> out) {
        this.out = out;
    }

    public void setFt(FileTable<Integer, FileInfo> ft) {
        this.ft = ft;
    }

    public MyIStack<IStmt> getSt() {
        return st;
    }

    public MyIDictionary<String, Integer> getDict() {
        return dict;
    }

    public MyIList<Integer> getOut() {
        return out;
    }

    public FileTable<Integer, FileInfo> getFt() {
        return ft;
    }
}
