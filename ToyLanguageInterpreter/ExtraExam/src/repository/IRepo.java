package repository;

import model.PrgState;

import java.util.List;

public interface IRepo {
    public void add(PrgState state);
    public PrgState getCurrentProgram();
    void logPrgStateExec(PrgState state);
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> lis);
    PrgState getById(int id);
    public IRepo setCurrent(int _current);
    public PrgState getCurrent();
    public void setAll(List<PrgState> _pr);
}
