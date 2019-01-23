package repository;

import model.PrgState;

public interface IRepo {
    public void add(PrgState state);
    public PrgState getCurrentProgram();
}
