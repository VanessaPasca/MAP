package repository;

import model.PrgState;

import java.util.List;

public interface IRepo {
    public void add(PrgState state);
    public PrgState getCurrentProgram();
    void logPrgStateExec(PrgState state);
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> lis);


}
