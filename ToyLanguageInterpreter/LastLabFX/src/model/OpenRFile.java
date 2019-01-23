package model;

import exceptions.FileException;
import utils.MyIDictionary;
import utils.MyIFileTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFile implements IStmt {
    private String varFileId;
    private String fileName;

    public OpenRFile(String varFileId, String fileName) {
        this.varFileId = varFileId;
        this.fileName = fileName;
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIFileTable<Integer, FileInfo> fileTable=state.getFileTable();
        if (fileTable.contains(new FileInfo(fileName,null))){
            throw new FileException("File "+fileName+" is already open!");
        }
        try {
            BufferedReader buff = new BufferedReader(new FileReader(fileName));
            FileInfo info=new FileInfo(fileName,buff);
            int id=IdGenerator.generateID();
            fileTable.add(id,info);
            MyIDictionary<String,Integer> dict=state.getSymTable();
            dict.setVal(varFileId,id);
        }catch(IOException ex){
            throw new FileException(ex.getMessage());
        }

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new OpenRFile(varFileId,fileName);
    }

    @Override
    public String toString() {
        return "open("+varFileId+","+fileName+");";
    }
}
