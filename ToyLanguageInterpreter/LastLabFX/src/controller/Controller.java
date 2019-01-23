package controller;

import exceptions.*;
import model.FileInfo;
import model.PrgState;
import repository.IRepo;
import utils.MyIFileTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepo repo;
    private int flag;
    private ExecutorService executor;

    public Controller(IRepo repo, int f) {
        this.repo = repo;
        this.flag = f;
    }

    public void display() {
        System.out.println("----------------------------------------------------------------------");
        List<PrgState> prgs=repo.getPrgList();
        for (PrgState state: prgs){
            System.out.println(state);
        }

    }

   void oneStepForAllPrg(List<PrgState> programs){
        //print the PrgState List into a file
        programs.forEach(prg-> repo.logPrgStateExec(prg));

        //prepare the list of callables
        List<Callable<PrgState>> callList=programs.stream()
                .map(prg->(Callable<PrgState>)(()->{return prg.oneStep();}))
                .collect(Collectors.toList());

        //instantiate a list for the new created programs
       List<PrgState> newPrgList=new ArrayList<>();
       //start the execution of the callables
       //it returns the list of new created PrgStates(namely threads)
        try {
            newPrgList = executor.invokeAll(callList)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (CollectionException |ExpressionException|FileException|
                                InterpreterException| RepositoryException |ExecutionException|InterruptedException e) {
                            System.out.println(e.getMessage());
                        } catch (Exception e){
                            System.out.println("Unknown cause exception!");
                        }
                        return null;
                    })
                    //choses only the programs, since only fork statements return a new state

                    .filter(p -> p != null)
                    .collect(Collectors.toList());
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }

        //adds possible new programs to the existing ones
        programs.addAll(newPrgList);

        //print the PrgState List into a log file
        programs.forEach(prg->repo.logPrgStateExec(prg));

        //save the current programs in the repository
        repo.setPrgList(programs);

    }

    public void completeEvaluation() {

        executor = Executors.newFixedThreadPool(2);
        //remove completed programs
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());

        while (prgList.size() > 0) {

            for (PrgState state : prgList) {
                HashMap<Integer,Integer> map=(
                        conservativeGarbageCollector(
                                state.getSymTable().getContent().values(),
                                state.getHeap().getContent()
                        ));
                state.getHeap().setContent(map);
                display();
            }
            oneStepForAllPrg(prgList);


            //remove completed programs

            prgList = removeCompletedPrg(repo.getPrgList());
        }

        display();
        executor.shutdownNow();

        List<PrgState> tmpList = repo.getPrgList();
        //CLOSE THE FILES
        for (PrgState state : tmpList) {
            MyIFileTable<Integer, FileInfo> map = state.getFileTable();
            for (Map.Entry<Integer, FileInfo> entry : map.getAll()) {
                FileInfo value = entry.getValue();
                try {
                    BufferedReader reader = value.getReader();
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }
        }
            //here the Repo still contains at least one Completed Prg
            //and its List<PrgState> is not empty.

            repo.setPrgList(prgList);

    }

    public void addProg(PrgState prog) {
        repo.add(prog);
    }

    public HashMap<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, HashMap<Integer, Integer> heap) {
        return (HashMap<Integer,Integer>)heap.entrySet().stream()
                .filter(e -> symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
       return inPrgList.stream()
               .filter(p -> p.isNotCompleted())
               .collect(Collectors.toList());
    }
}
