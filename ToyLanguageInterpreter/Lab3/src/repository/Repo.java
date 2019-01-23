package repository;

import exceptions.RepositoryException;
import model.PrgState;

import java.util.ArrayList;
import java.util.List;

public class Repo implements IRepo {
    private List<PrgState> programs;
    private int current;

    public Repo() {
        this.programs = new ArrayList<>();
        this.current = -1;
    }

    @Override
    public void add(PrgState state) {
        programs.add(state);
        this.current++;
    }

    @Override
    public PrgState getCurrentProgram() {
        if (programs.isEmpty()){
            throw new RepositoryException("Repository: the list of programs is empty");
        }
        return programs.get(current);
    }
}
