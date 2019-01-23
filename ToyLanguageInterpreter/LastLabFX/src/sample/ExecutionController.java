package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.FileInfo;
import model.IStmt;
import model.PrgState;
import repository.IRepo;

import java.io.Console;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ExecutionController implements Initializable {
    @FXML
    private TableView<Map.Entry<String, Integer>> tvSymbolTable;
    @FXML
    private Button btnOneStep;


    @FXML
    private TableView<Map.Entry<Integer, FileInfo>> tvFileTable;

    @FXML
    private TableView<Map.Entry<Integer, Integer>> tvLock;

    @FXML
    private TableView<Map.Entry<Integer, Integer>> tvHeap;

    @FXML
    private ListView<Integer> lstOut;

    @FXML

    private ListView<String> lstStack;

    @FXML
    private ListView<Integer> lstId;

    private IRepo repo;
    private ExecutorService executor;

    Iterable<Map.Entry<Integer, Integer>> heap;
    Iterable<Map.Entry<Integer, FileInfo>> fileTbl;
    Iterable<Map.Entry<String, Integer>> symTbl;
    Iterable<Map.Entry<Integer, Integer>> lock;
    Iterable<Integer> out;
    Iterable<IStmt> execStack;
    List<String> execStackStr;
    List<Integer> prgIds;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @SuppressWarnings("unchecked")
    public void initProgram(PrgState state, IRepo _repo) throws InterruptedException {

        repo=_repo;
        //get the program list and create a List<Integer> with their id's
        prgIds= repo.getPrgList().stream()
                .map(e->e.getId())
                .collect(Collectors.toList());

        //put the id's in the ListView
        ObservableList<Integer> obsId= FXCollections.observableArrayList(prgIds);
        lstId.setItems(obsId);

        /*
        Get the statements to be executed.
        Transform Iterable<IStmt> in List<IStmt>
         */
        execStack=state.getExeStack().getAll();
        List<IStmt> execList=new ArrayList<>();
        execStack.forEach(e->execList.add(e));

        //Transform the list to a list of strings
        execStackStr=execList.stream()
                .map(e->e.toString())
                .collect(Collectors.toList());

        //Make the observable object and set it to the list of statements
        ObservableList<String> execObs=FXCollections.observableArrayList(execStackStr);
        lstStack.setItems(execObs);


        symTbl=state.getSymTable().getAll();
        List<Map.Entry<String,Integer>> symList=new ArrayList<>();
        symTbl.forEach(e->symList.add(e));
        //observable list of map entries ({variable, value})
        ObservableList<Map.Entry<String,Integer>> symObs=FXCollections.observableArrayList(symList);

        //o coloana care va avea valori String
        TableColumn<Map.Entry<String,Integer>,String> varNameCoumn=new TableColumn<>("Variable");
        //sets that the values of the columns to be taken from the map entry, being the key
        varNameCoumn.setCellValueFactory(p->(ObservableValue<String>)new SimpleStringProperty(p.getValue().getKey()));;
        //O coloana cu valori Integer
        TableColumn<Map.Entry<String, Integer>, Integer> valueColumn = new TableColumn<>("Value");
        valueColumn.setCellValueFactory(p -> (ObservableValue<Integer>)new SimpleIntegerProperty(p.getValue().getValue()).asObject());
        tvSymbolTable.getColumns().clear();
        tvSymbolTable.getColumns().addAll(varNameCoumn,valueColumn);
        tvSymbolTable.setItems(symObs);

        fileTbl=state.getFileTable().getAll();
        List<Map.Entry<Integer,FileInfo>> fileList=new ArrayList<>();
        fileTbl.forEach(e->fileList.add(e));
        ObservableList<Map.Entry<Integer,FileInfo>> fileObs=FXCollections.observableArrayList(fileList);

        TableColumn<Map.Entry<Integer,FileInfo>,Integer> descrColumn=new TableColumn("Descr");
        descrColumn.setCellValueFactory(p -> (ObservableValue<Integer>)new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        TableColumn<Map.Entry<Integer, FileInfo>, String> fileColumn = new TableColumn<>("File");
        fileColumn.setCellValueFactory(p -> (ObservableValue<String>)new SimpleStringProperty(p.getValue().getValue().toString()));

        tvFileTable.getColumns().clear();
        tvFileTable.getColumns().addAll(descrColumn,fileColumn);
        tvFileTable.setItems(fileObs);

        heap = state.getHeap().getAll();
        List<Map.Entry<Integer, Integer>> heapList = new ArrayList<>();
        heapList.forEach(e -> heapList.add(e));
        ObservableList<Map.Entry<Integer, Integer>> heapObs = FXCollections.observableArrayList(heapList);

        TableColumn<Map.Entry<Integer, Integer>, Integer> pointerColumn = new TableColumn<>("Descr");
        pointerColumn.setCellValueFactory(p -> (ObservableValue<Integer>)new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        TableColumn<Map.Entry<Integer, Integer>, Integer> dataColumn = new TableColumn<>("File");
        dataColumn.setCellValueFactory(p -> (ObservableValue<Integer>)new SimpleIntegerProperty(p.getValue().getValue()).asObject());

        tvHeap.getColumns().clear();
        tvHeap.getColumns().addAll(pointerColumn, dataColumn);
        tvHeap.setItems(heapObs);

        // OUTPUT
        out = state.getOut().getAll();
        List<Integer> outList = new ArrayList<>();
        out.forEach(e -> outList.add(e));
        ObservableList<Integer> outObs = FXCollections.observableArrayList(outList);
        lstOut.setItems(outObs);

        //Event handler for the button
        btnOneStep.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //all the existing programs take one step
                allStepGUI();
                int nr=(int)repo.getPrgList().stream()
                        .filter(s-> s.isNotCompleted())
                        .count();
                //if the repo has no more programs not finished, the button disappears
                if (nr==0){
                    btnOneStep.disabledProperty();
                    btnOneStep.setVisible(false);


                }

                PrgState cstate;

                try{
                    //take the program which has the selected id from the List in the window
                    cstate = repo.getById(lstId.getSelectionModel().getSelectedItem());
                }catch(Exception ex){
                    cstate=repo.setCurrent(0).getCurrentProgram();
                }

                //recompute the prgIds
                prgIds=_repo.getPrgList().stream()
                        .map(e->e.getId())
                        .collect(Collectors.toList());

                //if it's not equal with what we already have, update
                if (!prgIds.equals(obsId)){
                    obsId.setAll(prgIds);
                    lstId.setItems(obsId);
                }

                //Update the rest of the data according to the changes made by oneStep for all programs
                execStack = cstate.getExeStack().getAll();
                execList.clear();
                execStack.forEach(e -> execList.add(e));
                execStackStr = execList.parallelStream().map(e -> e.toString()).collect(Collectors.toList());
                execObs.setAll(execStackStr);
                lstStack.setItems(execObs);

                // SYMBOL TABLE
                symTbl = cstate.getSymTable().getAll();
                symList.clear();
                symTbl.forEach(e -> symList.add(e));
                symObs.setAll(symList);
                tvSymbolTable.setItems(symObs);

                // FILE TABLE
                fileTbl = cstate.getFileTable().getAll();
                fileList.clear();
                fileTbl.forEach(e -> fileList.add(e));
                fileObs.setAll(fileList);
                tvFileTable.setItems(fileObs);

                // HEAP
                heap = cstate.getHeap().getAll();
                heapList.clear();
                heap.forEach(e -> heapList.add(e));
                heapObs.setAll(heapList);
                tvHeap.setItems(heapObs);



                // OUTPUT
                out = cstate.getOut().getAll();
                outList.clear();
                out.forEach(e -> outList.add(e));
                outObs.setAll(outList);
                lstOut.setItems(outObs);;


            }
        });

        //add a handler for when we choose another id from the list
        lstId.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Integer>()
                {
                    public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue)
                    {
                        //daca nu am selectat nimic punem ca selectat id-ul primului program din lista
                        if (newValue == null)
                            newValue = repo.setCurrent(0).getCurrent().getId();
                        PrgState nstate = repo.getById(newValue);

                        //set the execution stack and sym table, them being the only ones that are not shared

                        // EXECUTION STACK
                        execStack = nstate.getExeStack().getAll();
                        execList.clear();
                        execStack.forEach(e -> execList.add(e));
                        execStackStr = execList.stream().map(e -> e.toString()).collect(Collectors.toList());
                        execObs.setAll(execStackStr);
                        lstStack.setItems(execObs);

                        // SYMBOL TABLE
                        symTbl = nstate.getSymTable().getAll();
                        symList.clear();
                        symTbl.forEach(e -> symList.add(e));
                        symObs.setAll(symList);
                        tvSymbolTable.setItems(symObs);

                    };
                });


    }
    //mircea.suceveanu@siemens.com

    public List<PrgState> removeCompletedPrg(List<PrgState> lst)
    {
        return lst.stream().filter(x -> x.isNotCompleted()).collect(Collectors.toList());
    }

    public void oneStepAllPrg(List<PrgState> l)
    {
        List<Callable<PrgState>> callList = l.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());
        List<PrgState> resList;
        try
        {
            resList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        }
                        catch (ExecutionException ex){
                            Alert alert = new Alert(Alert.AlertType.ERROR, ex.toString());
                            System.out.println(ex.toString());
                            btnOneStep.disableProperty();
                            btnOneStep.setVisible(false);

                            alert.show();
                        }
                        catch (Exception ex)
                        {
                            System.out.println(ex);
                        }
                        return null;
                    })
                    .filter(p -> p != null)
                    .collect(Collectors.toList());
        }
        catch (InterruptedException e)
        {
            resList = new ArrayList<>();
        }
        l.forEach(p -> repo.logPrgStateExec(p));
        l.forEach(p -> p.getHeap().setFromMap(
                garbageCollector(
                        p.getSymTable().getAsMap().values(),
                        p.getHeap().getAsMap())));
        l.addAll(resList);
        repo.setAll(l);
    }

    public void allStepGUI()
    {
        executor = Executors.newFixedThreadPool(2);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgList());
        if(prgList.isEmpty())
        {
            return;
        }
        else
        {
            oneStepAllPrg(prgList);
        }
        //repo.setAll(removeCompletedPrg(repo.getAll()));
        executor.shutdown();
    }

    public void allStep()
    {
        executor = Executors.newFixedThreadPool(2);
        repo.getPrgList().forEach(p -> repo.logPrgStateExec(p));
        while(true)
        {
            List<PrgState> prglist = removeCompletedPrg(repo.getPrgList());
            if(prglist.isEmpty())
                break;
            oneStepAllPrg(prglist);
        }
        executor.shutdownNow();
    }

    public Map<Integer, Integer> garbageCollector(Collection<Integer> symTableVal,
                                                  Map<Integer, Integer> heap)
    {
        return heap.entrySet().stream().filter(e -> symTableVal.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
