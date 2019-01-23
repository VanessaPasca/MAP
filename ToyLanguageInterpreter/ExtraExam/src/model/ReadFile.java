package model;

import exceptions.CollectionException;
import exceptions.FileException;
import utils.MyIDictionary;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStmt {
    private Exp expFileID;
    private String  varName;

    public ReadFile(Exp expFileID, String varName) {
        this.expFileID = expFileID;
        this.varName = varName;
    }



    @Override
    public PrgState execute(PrgState state) {
        MyIDictionary<String,Integer> dict=state.getSymTable();
        try {
            int evaluation=expFileID.eval(state.getSymTable(),state.getHeap());

            FileInfo fi = state.getFileTable().get(evaluation);
            BufferedReader buff = fi.getReader();
            String what = buff.readLine();
            int value = 0;
            if (what != null) {
                value = Integer.parseInt(what);
            }

            state.getSymTable().setVal(varName, value);
        }catch(CollectionException ex){
            throw new FileException(ex.getMessage());
        }catch(IOException ex){
            throw new FileException(ex.getMessage());
        }

        return null;

    }

    @Override
    public IStmt deepCopy() {
        return new ReadFile(expFileID.deepCopy(),varName);
    }

    @Override
    public String toString() {
        return "read("+expFileID+","+varName+");";
    }
}





