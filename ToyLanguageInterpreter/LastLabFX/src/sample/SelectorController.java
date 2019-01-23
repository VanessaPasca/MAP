package sample;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.*;
import repository.IRepo;
import repository.Repo;
import utils.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class SelectorController implements Initializable {
    @FXML
    private ListView<String> listPrgs;

    @FXML
    private Button btnSelect;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<PrgState> prgs=new ArrayList<>();
        IStmt ex1= new CompStmt(new AssignStmt("v",new ConstExpr(2)), new PrintStmt(new
                VarExpr("v")));
        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex1,1
        ));


        IStmt ex2 = new CompStmt(new AssignStmt("a", new ArithmExpr(new ConstExpr(2),new
                ArithmExpr(new ConstExpr(3), new ConstExpr(5),'*'),'+')),
                new CompStmt(new AssignStmt("b",new ArithmExpr(new VarExpr("a"), new
                        ConstExpr(1),'+')), new PrintStmt(new VarExpr("b"))));
        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex2,
                1
        ));


        IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithmExpr(new ConstExpr(2), new
                ConstExpr(2),'-')),
                new CompStmt(new IfStmt(new VarExpr("a"),new AssignStmt("v",new ConstExpr(2)), new
                        AssignStmt("v", new ConstExpr(3))), new PrintStmt(new VarExpr("v"))));
        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex3,1
        ));


        IStmt ex4=new CompStmt(new CompStmt(new OpenRFile("var_f","test.in"),new CompStmt(new ReadFile(new VarExpr("var_f"),"var_c"),new PrintStmt(new VarExpr("var_c")))),
                new CompStmt(new IfStmt(new BoolExpr(new VarExpr("var_c"),new ConstExpr(1),">"),
                        new CompStmt(new ReadFile(new VarExpr("var_f"),"var_c"),new PrintStmt(new VarExpr("var_c"))),new PrintStmt(new ConstExpr(0))),

                        new CloseRFile(new VarExpr("var_f"))));
        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex4,1
        ));


        IStmt ex5=new CompStmt(new CompStmt(new OpenRFile("var_f","test.in"),new CompStmt(new ReadFile(new ArithmExpr(new VarExpr("var_f"),new ConstExpr(2),'+'),"var_c"),new PrintStmt(new VarExpr("var_c")))),
                new CompStmt(new IfStmt(new BoolExpr(new VarExpr("var_c"),new ConstExpr(1),">"),
                        new CompStmt(new ReadFile(new VarExpr("var_f"),"var_c"),new PrintStmt(new VarExpr("var_c"))),new PrintStmt(new ConstExpr(0))),

                        new CloseRFile(new VarExpr("var_f"))));

        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex5,1
        ));


        IStmt ex6=new CompStmt(new CompStmt(new AssignStmt("v",new ConstExpr(10)),new New("v",new ConstExpr(20))),
                new CompStmt(new New("a",new ConstExpr(22)),new PrintStmt(new VarExpr("v"))));
        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex6,1
        ));


        IStmt ex7=new CompStmt(new CompStmt(new AssignStmt("v",new ConstExpr(10)),new New("v",new ConstExpr(20))),
                new CompStmt(new New("a",new ConstExpr(22)),new CompStmt(new PrintStmt(
                        new ArithmExpr(new ConstExpr(100),new RH("v"),'+')),
                        new PrintStmt(new ArithmExpr(new ConstExpr(100), new RH("a"),'+')))));
        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex7,1
        ));


        IStmt ex8=new CompStmt(new CompStmt(new AssignStmt("v",new ConstExpr(10)),new New("v",new ConstExpr(20))),
                new CompStmt(new CompStmt(new New("a",new ConstExpr(22)),new WH("a",new ConstExpr(30))),
                        new CompStmt(new PrintStmt(new VarExpr("a")),new PrintStmt(new RH("a")))));

        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex8,1
        ));

        IStmt ex9=new CompStmt(new CompStmt(new AssignStmt("v",new ConstExpr(10)),new New("v",new ConstExpr(20))),
                new CompStmt(new CompStmt(new New("a",new ConstExpr(22)),new WH("a",new ConstExpr(30))),
                        new CompStmt(new PrintStmt(new VarExpr("a")),new CompStmt(new PrintStmt(new RH("a")),new AssignStmt("a", new ConstExpr(0))))));

        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex9,1
        ));


        IStmt ex10=new CompStmt(new CompStmt(new AssignStmt("v",new ConstExpr(6)),new WhileStmt(new ArithmExpr(
                new VarExpr("v"),new ConstExpr(4),'-'),new CompStmt(
                new PrintStmt(new VarExpr("v")),new AssignStmt("v",new ArithmExpr(new VarExpr("v"),new ConstExpr(1),'-'))
        ))),new PrintStmt(new VarExpr("v")));

        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex10,1
        ));


        IStmt ex11=new CompStmt(new CompStmt(new AssignStmt("v",new ConstExpr(10)),
                new CompStmt(new New("a",new ConstExpr(22)),new ForkStmt(
                        new CompStmt(new CompStmt(new WH("a",new ConstExpr(30)),
                                new AssignStmt("v",new ConstExpr(32))),
                                new CompStmt(new PrintStmt(new VarExpr("v")),new PrintStmt(
                                        new RH("a")
                                ))
                        )
                ))),new CompStmt(new PrintStmt(new VarExpr("v")),new PrintStmt(new RH("a"))));
        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex11,1
        ));

        IStmt ex12=new CompStmt(new CompStmt(new AssignStmt("v1",new ConstExpr(0)),
                new AssignStmt("v2",new ConstExpr(1))),new CompStmt(
                        new IfStmt(new OrExpr(new BoolExpr(new VarExpr("v1"),new ConstExpr(0),"=="),
                                new BoolExpr(new VarExpr("v2"),new ConstExpr(0),"==")),
                                new PrintStmt(new ConstExpr(1)),new PrintStmt(new ConstExpr(0))),
                new IfStmt(new AndExpr(new BoolExpr(new VarExpr("v1"),new ConstExpr(0),"=="),
                        new BoolExpr(new VarExpr("v2"),new ConstExpr(0),"==")),
                        new PrintStmt(new ConstExpr(1)),new PrintStmt(new ConstExpr(0)))
        ));

        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex12,1
        ));

        IStmt ex13=new CompStmt(new AssignStmt("v",new ConstExpr(0)),new CompStmt(
                new CompStmt(new IncrStmt("v"),new PrintStmt(new VarExpr("v"))),
                new CompStmt(new DecrStmt("v"),new PrintStmt(new VarExpr("v")))
        ));

        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex13,1
        ));

        IStmt ex14=new CompStmt(new AssignStmt("v",new ConstExpr(0)),
                new RepeatStmt(new CompStmt(new IncrStmt("v"),new PrintStmt(new VarExpr("v"))),new BoolExpr(new VarExpr("v"),new
                        ConstExpr(10),"==")));

        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex14,1
        ));

        ArrayList<Case> cases=new ArrayList<>();
        cases.add(new Case(new ConstExpr(0),new PrintStmt(new VarExpr("v"))));
        cases.add(new Case(new ConstExpr(1),new PrintStmt(new VarExpr("v"))));
        cases.add(new Case(new ConstExpr(2),new PrintStmt(new VarExpr("v"))));
        cases.add(new Case(new ConstExpr(5),new IfStmt(new BoolExpr(new ConstExpr(6),new ConstExpr(7),">"),
                new PrintStmt(new ConstExpr(6)),new PrintStmt(new ConstExpr(7)))));
        IStmt ex15=new CompStmt(new AssignStmt("v",new ConstExpr(5)),
                new SwitchStmt(new VarExpr("v"),cases,new PrintStmt(new ConstExpr(1000))));

        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex15,1
        ));

        IStmt c1=new CompStmt(new CompStmt(
                new AssignStmt("a",new ConstExpr(1)),
                new AssignStmt("b",new ConstExpr(2))
        ), new AssignStmt("c",new ConstExpr(5)));


        Case cs1=new Case(new ArithmExpr(new VarExpr("b"),new VarExpr("c"),'*'),new CompStmt(
                new PrintStmt(new VarExpr("a")),new PrintStmt(new VarExpr("b"))));
        Case cs2=new Case(
                new ConstExpr(10),new CompStmt(
                new PrintStmt(new ConstExpr(100)),new PrintStmt(new ConstExpr(200))));

        IStmt c2=new SwitchStmt(new ArithmExpr(new VarExpr("a"),new ConstExpr(10),'*'),cs1,cs2,new PrintStmt(new ConstExpr(300)));
        IStmt ex16=new CompStmt(new CompStmt(
                c1,c2
        ),new PrintStmt(new ConstExpr(300)));


        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex16,1
        ));



        List<String> stringList=prgs.stream()
                .map(p->p.getOriginalProgram().toString())
                .collect(Collectors.toList());

        ObservableList<String> obsvList= FXCollections.observableArrayList(stringList);

        listPrgs.setItems(obsvList);

        btnSelect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index=listPrgs.getSelectionModel().getSelectedIndex();

                if (index == -1){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "No program selected!");
                    alert.show();
                    return;
                }

                PrgState prog=prgs.get(index);

                try{
                    //close current window
                    Stage oldStage=(Stage) btnSelect.getScene().getWindow();
                    oldStage.close();

                    //open new window

                    FXMLLoader loader=new FXMLLoader(getClass().getResource("execution.fxml"));
                    AnchorPane root=loader.load();

                    Scene scena=new Scene(root,900,600);

                    Stage stg=new Stage();
                    stg.setScene(scena);
                    stg.setTitle("Execution Window");

                    ExecutionController cont=loader.getController();

                    IRepo repo=new Repo("logFile.txt");
                    repo.add(prog);
                    cont.initProgram(prog,repo);

                    stg.show();

                }catch(Exception e){
                    e.printStackTrace();

                }
            }
        });
    }
}
