package repository;

import exceptions.RepositoryException;
import model.FileInfo;
import model.IStmt;
import model.PrgState;
import utils.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Repo implements IRepo {
    private List<PrgState> programs;
    private String logFilePath;
    private int current;

    public Repo() {
        this.programs = new ArrayList<>();
        this.current = -1;
    }
    public Repo(String fp) {
        this.programs = new ArrayList<>();
        this.logFilePath=fp;
        this.current = -1;
    }

    public Repo(PrgState state,String fp) {
        this.programs = new ArrayList<>();
        programs.add(state);
        this.logFilePath=fp;
        this.current = 0;
    }



    @Override
    public void add(PrgState state) {
        programs.add(state);
        this.current++;
    }

    @Override
    public PrgState getCurrentProgram() {
        if (programs.isEmpty()){
            throw new RepositoryException("Repository: the list of programs is empty");
        }
        return programs.get(current);
    }

    @Override
    public List<PrgState> getPrgList() {
       return this.programs;
    }

    @Override
    public void setPrgList(List<PrgState> lis) {
        this.programs=lis;
    }

    @Override
    public void logPrgStateExec(PrgState state) {
        // append: true
        try(PrintWriter logFile=new PrintWriter(new BufferedWriter(new FileWriter(this.logFilePath, true)))){
            PrgState p=programs.get(current);
            MyIStack<IStmt> exe=p.getExeStack();
            MyIDictionary<String,Integer> dict=p.getSymTable();
            MyIList<Integer> out=p.getOut();
            MyIFileTable<Integer, FileInfo> ft=p.getFileTable();
            MyIHeap<Integer,Integer> heap=p.getHeap();

            logFile.println("ExeStack:");
            for (IStmt stm:exe.getAll()){
                logFile.println("\t\t"+stm);
            }

            logFile.println("SymTable:");
            for (Map.Entry<String,Integer> e:dict.getAll()){
                logFile.println("\t\t"+e.getKey()+" -> "+e.getValue());
            }

            logFile.println("Output:");
            for (Integer value:out.getAll()){
                logFile.println("\t\t"+value);
            }

            logFile.println("FileTable");
            for (Map.Entry<Integer, FileInfo> e:ft.getAll()){
                logFile.println("\t\t"+e.getKey()+"-->"+e.getValue());
            }
            logFile.println("Heap");
            for (Map.Entry<Integer, Integer> e: heap.getAll()){
                logFile.println("\t\t"+e.getKey()+" --> "+e.getValue());
            }
            logFile.println("--------------------------");

        }catch(IOException e){

        }
    }
}
