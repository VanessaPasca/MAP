package model;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStmt {
    private Exp expFileId;
    private String varName;

    public ReadFile(Exp expFileId, String varName) {
        this.expFileId = expFileId;
        this.varName = varName;
    }

    @Override
    public PrgState execute(PrgState prg) {
        try{
            int val=expFileId.eval(prg.getDict());
            FileInfo info=prg.getFt().getVal(val);
            BufferedReader buff=info.getBuff();
            String what=buff.readLine();
            int rez=0;
            if (what!=null){
                rez=Integer.parseInt(what);
            }
            prg.getDict().setVal(varName,rez);


        }catch(IOException e){

        }
        return prg;
    }
}
