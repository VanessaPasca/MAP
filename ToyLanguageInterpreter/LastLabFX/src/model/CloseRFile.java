package model;

import exceptions.CollectionException;
import exceptions.FileException;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStmt {
    Exp expFileId;

    public CloseRFile(Exp expFileId) {
        this.expFileId = expFileId;
    }

    @Override
    public PrgState execute(PrgState state) {
        try{
            int evaluation=expFileId.eval(state.getSymTable(),state.getHeap());

            FileInfo info=state.getFileTable().get(evaluation);
            BufferedReader buff=info.getReader();
            buff.close();
            state.getFileTable().remove(evaluation);


        }catch(CollectionException ex){
            throw new FileException(ex.getMessage());
        }catch(IOException ex){
            throw new FileException(ex.getMessage());
        }
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CloseRFile(expFileId.deepCopy());
    }

    @Override
    public String toString() {
        return "close("+expFileId+");";
    }
}
