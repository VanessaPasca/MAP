package model;

import java.io.Serializable;

public interface IStmt extends Serializable {
    public PrgState execute(PrgState state);
    public IStmt deepCopy();
}
