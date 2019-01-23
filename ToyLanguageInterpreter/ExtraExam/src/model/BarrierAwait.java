package model;

import exceptions.CollectionException;

import java.util.List;

public class BarrierAwait implements IStmt {
    private String var;

    public BarrierAwait(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return "barrierAwait{" +var+");";
    }

    @Override
    public PrgState execute(PrgState state) {
       if (!state.getSymTable().has(var)){
           throw new CollectionException(var+" not in SymbolTable");
       }

       synchronized (state.getCyclicBarrier()){
           int index=state.getSymTable().getVal(var);
           if (!state.getCyclicBarrier().contains(index)){
               throw new CollectionException("Index "+index+" not in BarrierTable!");
           }

           BarrierPair<List<Integer>,Integer> pair=state.getCyclicBarrier().get(index);
           if(pair.getFirst().size() < pair.getSecond()){
               if (pair.getFirst().contains(state.getId())){

                   state.getExeStack().push(this);
               }else{

                   List<Integer> l=state.getCyclicBarrier().get(index).getFirst();
                   l.add(state.getId());
                   state.getCyclicBarrier().update(index, new BarrierPair<>(l,state.getCyclicBarrier().get(index).getSecond()-1));
                   state.getExeStack().push(this);

               }
           }else{
               if (!pair.getFirst().contains(state.getId())){
                   List<Integer> l=state.getCyclicBarrier().get(index).getFirst();
                   l.add(state.getId());
                   state.getCyclicBarrier().update(index, new BarrierPair<>(l,state.getCyclicBarrier().get(index).getSecond()-1));
               }

           }
       }
       return null;
    }

    @Override
    public IStmt deepCopy() {
        return new BarrierAwait(var);
    }
}
