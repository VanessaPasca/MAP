package model;

import java.io.BufferedReader;
import java.io.File;
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
    public PrgState execute(PrgState prg) {
        FileTable<Integer, FileInfo> fileTable=prg.getFt();
        if (fileTable.contains(new FileInfo(fileName,null))){
            throw new RuntimeException();
        }
        try {
            BufferedReader buff=new BufferedReader(new FileReader(fileName));
            FileInfo info=new FileInfo(fileName,buff);
            int id=IdGenerator.generateID();
            prg.getFt().setVal(id,info);
            prg.getDict().setVal(varFileId,id);
        }catch(IOException ex){

        }
        return prg;
    }
}
