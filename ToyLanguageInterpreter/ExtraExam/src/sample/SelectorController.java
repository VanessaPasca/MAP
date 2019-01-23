package sample;

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
                ex1,1,new LatchTable<Integer,Integer>(), new CyclicBarrier<Integer, BarrierPair<List<Integer>,Integer>>()
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
                1,new LatchTable<Integer,Integer>(),new CyclicBarrier<Integer, BarrierPair<List<Integer>,Integer>>()
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
                ex3,1,new LatchTable<Integer,Integer>(), new CyclicBarrier<Integer, BarrierPair<List<Integer>,Integer>>()
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
                ex4,1,new LatchTable<Integer,Integer>(), new CyclicBarrier<Integer, BarrierPair<List<Integer>,Integer>>()
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
                ex5,1,new LatchTable<Integer,Integer>(), new CyclicBarrier<Integer, BarrierPair<List<Integer>,Integer>>()
        ));


        IStmt ex6=new CompStmt(new CompStmt(new AssignStmt("v",new ConstExpr(10)),new New("v",new ConstExpr(20))),
                new CompStmt(new New("a",new ConstExpr(22)),new PrintStmt(new VarExpr("v"))));
        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex6,1,new LatchTable<Integer,Integer>(), new CyclicBarrier<Integer, BarrierPair<List<Integer>,Integer>>()
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
                ex7,1,new LatchTable<Integer,Integer>(), new CyclicBarrier<Integer, BarrierPair<List<Integer>,Integer>>()
        ));


        IStmt ex8=new CompStmt(new CompStmt(new AssignStmt("v",new ConstExpr(10)),new New("v",new ConstExpr(20))),
                new CompStmt(new CompStmt(new New("a",new ConstExpr(22)),new WH("a",new ConstExpr(30))),
                        new CompStmt(new PrintStmt(new VarExpr("a")),new PrintStmt(new RH("a")))));

        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex8,1,new LatchTable<Integer,Integer>(), new CyclicBarrier<Integer, BarrierPair<List<Integer>,Integer>>()
        ));

        IStmt ex9=new CompStmt(new CompStmt(new AssignStmt("v",new ConstExpr(10)),new New("v",new ConstExpr(20))),
                new CompStmt(new CompStmt(new New("a",new ConstExpr(22)),new WH("a",new ConstExpr(30))),
                        new CompStmt(new PrintStmt(new VarExpr("a")),new CompStmt(new PrintStmt(new RH("a")),new AssignStmt("a", new ConstExpr(0))))));

        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex9,1,new LatchTable<Integer,Integer>(), new CyclicBarrier<Integer, BarrierPair<List<Integer>,Integer>>()
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
                ex10,1,new LatchTable<Integer,Integer>(), new CyclicBarrier<Integer, BarrierPair<List<Integer>,Integer>>()
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
                ex11,1,new LatchTable<Integer,Integer>(), new CyclicBarrier<Integer, BarrierPair<List<Integer>,Integer>>()
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
                ex12,1,new LatchTable<Integer,Integer>(), new CyclicBarrier<Integer, BarrierPair<List<Integer>,Integer>>()
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
                ex13,1,new LatchTable<Integer,Integer>(), new CyclicBarrier<Integer, BarrierPair<List<Integer>,Integer>>()
        ));

        IStmt ex14=new CompStmt(new AssignStmt("v",new ConstExpr(0)),
                new RepeatStmt(new CompStmt(new IncrStmt("v"),new PrintStmt(new VarExpr("v"))),new BoolExpr(new VarExpr("v"),new
                        ConstExpr(10),"==")));

        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex14,1,new LatchTable<Integer,Integer>(), new CyclicBarrier<Integer, BarrierPair<List<Integer>,Integer>>()
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
                ex15,1, new LatchTable<Integer,Integer>(), new CyclicBarrier<Integer, BarrierPair<List<Integer>,Integer>>()
        ));

        IStmt a1 = new CompStmt(
                new  CompStmt(
                        new New("v1",new ConstExpr(2)),
                        new New("v2",new ConstExpr(3))
                ),
                new  CompStmt(
                        new New("v3",new ConstExpr(4)),
                        new NewLatch("cnt",new RH("v2"))
                )
        );
        IStmt a2 = new ForkStmt(
                new  CompStmt(
                        new  CompStmt(
                                new WH("v1",new ArithmExpr(new RH("v1"),new ConstExpr(10),'*')),
                                new PrintStmt(new RH("v1"))
                        ),
                        new CountDown("cnt")
                )
        );
       IStmt a3 = new ForkStmt(
                new  CompStmt(
                        new  CompStmt(
                                new WH("v2",new ArithmExpr(new RH("v2"),new ConstExpr(10),'*')),
                                new PrintStmt(new RH("v2"))
                        ),
                        new CountDown("cnt")
                )
        );
        IStmt a4 = new ForkStmt(
                new  CompStmt(
                        new  CompStmt(
                                new WH("v3",new ArithmExpr(new RH("v3"),new ConstExpr(10),'*')),
                                new PrintStmt(new RH("v3"))
                        ),
                        new CountDown("cnt")
                )
        );

        IStmt a5 = new  CompStmt(
                new  CompStmt(
                        new LatchAwait("cnt"),
                        new PrintStmt(new VarExpr("cnt"))
                ),
                new  CompStmt(
                        new CountDown("cnt"),
                        new PrintStmt(new VarExpr("cnt"))
                )
        );

        IStmt ex16 = new  CompStmt(
                new  CompStmt(a1,a2),
                new  CompStmt(new  CompStmt(a3,a4),a5)
        );

        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex16,1,new LatchTable<Integer,Integer>(), new CyclicBarrier<Integer, BarrierPair<List<Integer>,Integer>>()
        ));

        IStmt ex17=new CompStmt(new NewBarrier("b",new ConstExpr(2)),
                new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new ConstExpr(0)),new CompStmt(
                        new BarrierAwait("b"),new PrintStmt(new ConstExpr(1))))),new CompStmt(new BarrierAwait("b"),new PrintStmt(new ConstExpr(1)))));


        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex17,1,new LatchTable<Integer,Integer>(), new CyclicBarrier<Integer, BarrierPair<List<Integer>,Integer>>()
        ));

        IStmt ss1=new CompStmt(new CompStmt(
                new New("v1",new ConstExpr(2)),new New("v2",new ConstExpr(3))),
                new CompStmt(
                        new New("v3",new ConstExpr(4)),
                        new NewBarrier("cnt",new RH("v2")))
                );

        IStmt ss2=new ForkStmt(new CompStmt(new CompStmt(
                new BarrierAwait("cnt"),
                new WH("v1",new ArithmExpr(new RH("v1"),new ConstExpr(10),'*'))
        ),
                new PrintStmt(new RH("v1"))));

        IStmt ss3=new ForkStmt(new CompStmt(
                new CompStmt(
                        new BarrierAwait("cnt"),
                        new WH("v2",new ArithmExpr(new RH("v2"),new ConstExpr(10),'*'))
                ),new CompStmt(
                new WH("v2",new ArithmExpr(new RH("v2"),new ConstExpr(10),'*')),
                new PrintStmt(new RH("v2"))
        )
        ));

        IStmt ss4=new CompStmt(
                new BarrierAwait("cnt"),
                new PrintStmt(new RH("v3"))
        );

        IStmt ex18=new CompStmt(
                new CompStmt(ss1,ss2),
                new CompStmt(ss3,ss4)
        );

        prgs.add(new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex18,1,new LatchTable<Integer,Integer>(), new CyclicBarrier<Integer, BarrierPair<List<Integer>,Integer>>()
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

                    Scene scena=new Scene(root,1200,600);

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
