package model;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStmt{
    Exp expFileId;

    public CloseRFile(Exp expFileId) {
        this.expFileId = expFileId;
    }

    @Override
    public PrgState execute(PrgState prg) {
        try{
            int val=expFileId.eval(prg.getDict());
            FileInfo info=prg.getFt().getVal(val);
            BufferedReader buff=info.getBuff();
            buff.close();
            prg.getFt().remove(val);
        }catch(IOException e){

        }
        return prg;
    }
}
