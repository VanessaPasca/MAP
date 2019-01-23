package controller;

import model.IStmt;
import model.MyIStack;
import model.PrgState;
import repository.IRepo;

public class Controller {
    private IRepo repo;
    private int flag;
    public Controller(IRepo repo, int f) {
        this.repo = repo;
        this.flag = f;
    }
    public void display(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println(""+repo.getCurrentProgram());
    }

    public void oneStep(){
        PrgState state=repo.getCurrentProgram();
        MyIStack<IStmt> st=state.getExeStack();
        if (!st.isEmpty()){
            IStmt current=st.pop();
            current.execute(state);
        }
        if (flag==1)
        {display();}
    }

    public void completeEvaluation(){
        PrgState state = repo.getCurrentProgram();
        while(!state.getExeStack().isEmpty()){
            oneStep();
        }
        if (this.flag==0){
            display();
        }
    }

    public void addProg(PrgState prog){
        repo.add(prog);
    }
}
