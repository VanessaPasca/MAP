package main;

import controller.Controller;
import model.*;
import repository.IRepo;
import repository.Repo;
import utils.*;
import view.ExitCommand;
import view.RunExample;
import view.TextMenu;

public class Interpreter {
    public static void main(String[] args) {
        IStmt ex1= new CompStmt(new AssignStmt("v",new ConstExpr(2)), new PrintStmt(new
                VarExpr("v")));
        PrgState prg1=new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex1,1
        );
        IRepo repo1=new Repo(prg1,"log1.txt");
        Controller cont1=new Controller(repo1,1);

        IStmt ex2 = new CompStmt(new AssignStmt("a", new ArithmExpr(new ConstExpr(2),new
                ArithmExpr(new ConstExpr(3), new ConstExpr(5),'*'),'+')),
                new CompStmt(new AssignStmt("b",new ArithmExpr(new VarExpr("a"), new
                        ConstExpr(1),'+')), new PrintStmt(new VarExpr("b"))));
        PrgState prg2=new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex2,
                1
        );
        IRepo repo2=new Repo(prg2,"log1.txt");
        Controller cont2=new Controller(repo2,1);

        IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithmExpr(new ConstExpr(2), new
                ConstExpr(2),'-')),
                new CompStmt(new IfStmt(new VarExpr("a"),new AssignStmt("v",new ConstExpr(2)), new
                        AssignStmt("v", new ConstExpr(3))), new PrintStmt(new VarExpr("v"))));
        PrgState prg3=new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex3,1
        );
        IRepo repo3=new Repo(prg3,"log1.txt");
        Controller cont3=new Controller(repo3,1);

        IStmt ex4=new CompStmt(new CompStmt(new OpenRFile("var_f","test.in"),new CompStmt(new ReadFile(new VarExpr("var_f"),"var_c"),new PrintStmt(new VarExpr("var_c")))),
                new CompStmt(new IfStmt(new BoolExpr(new VarExpr("var_c"),new ConstExpr(1),">"),
                        new CompStmt(new ReadFile(new VarExpr("var_f"),"var_c"),new PrintStmt(new VarExpr("var_c"))),new PrintStmt(new ConstExpr(0))),

                        new CloseRFile(new VarExpr("var_f"))));
        PrgState prg4=new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex4,1
        );
        IRepo repo4=new Repo(prg4,"log1.txt");
        Controller cont4=new Controller(repo4,1);

        IStmt ex5=new CompStmt(new CompStmt(new OpenRFile("var_f","test.in"),new CompStmt(new ReadFile(new ArithmExpr(new VarExpr("var_f"),new ConstExpr(2),'+'),"var_c"),new PrintStmt(new VarExpr("var_c")))),
                new CompStmt(new IfStmt(new BoolExpr(new VarExpr("var_c"),new ConstExpr(1),">"),
                        new CompStmt(new ReadFile(new VarExpr("var_f"),"var_c"),new PrintStmt(new VarExpr("var_c"))),new PrintStmt(new ConstExpr(0))),

                        new CloseRFile(new VarExpr("var_f"))));

        PrgState prg5=new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex5,1
        );
        IRepo repo5=new Repo(prg5,"log1.txt");
        Controller cont5=new Controller(repo5,1);

        IStmt ex6=new CompStmt(new CompStmt(new AssignStmt("v",new ConstExpr(10)),new New("v",new ConstExpr(20))),
                new CompStmt(new New("a",new ConstExpr(22)),new PrintStmt(new VarExpr("v"))));
        PrgState prg6=new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex6,1
        );
        IRepo repo6=new Repo(prg6,"log1.txt");
        Controller cont6=new Controller(repo6,1);

        IStmt ex7=new CompStmt(new CompStmt(new AssignStmt("v",new ConstExpr(10)),new New("v",new ConstExpr(20))),
                new CompStmt(new New("a",new ConstExpr(22)),new CompStmt(new PrintStmt(
                        new ArithmExpr(new ConstExpr(100),new RH("v"),'+')),
                        new PrintStmt(new ArithmExpr(new ConstExpr(100), new RH("a"),'+')))));
        PrgState prg7=new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex7,1
        );
        IRepo repo7=new Repo(prg7,"log1.txt");
        Controller cont7=new Controller(repo7,1);

        IStmt ex8=new CompStmt(new CompStmt(new AssignStmt("v",new ConstExpr(10)),new New("v",new ConstExpr(20))),
                new CompStmt(new CompStmt(new New("a",new ConstExpr(22)),new WH("a",new ConstExpr(30))),
                        new CompStmt(new PrintStmt(new VarExpr("a")),new PrintStmt(new RH("a")))));

        PrgState prg8=new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex8,1
        );
        IRepo repo8=new Repo(prg8,"log1.txt");
        Controller cont8=new Controller(repo8,1);

        IStmt ex9=new CompStmt(new CompStmt(new AssignStmt("v",new ConstExpr(10)),new New("v",new ConstExpr(20))),
                new CompStmt(new CompStmt(new New("a",new ConstExpr(22)),new WH("a",new ConstExpr(30))),
                        new CompStmt(new PrintStmt(new VarExpr("a")),new CompStmt(new PrintStmt(new RH("a")),new AssignStmt("a", new ConstExpr(0))))));

        PrgState prg9=new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex9,1
        );
        IRepo repo9=new Repo(prg9,"log1.txt");
        Controller cont9=new Controller(repo9,1);

        IStmt ex10=new CompStmt(new CompStmt(new AssignStmt("v",new ConstExpr(6)),new WhileStmt(new ArithmExpr(
                new VarExpr("v"),new ConstExpr(4),'-'),new CompStmt(
                        new PrintStmt(new VarExpr("v")),new AssignStmt("v",new ArithmExpr(new VarExpr("v"),new ConstExpr(1),'-'))
        ))),new PrintStmt(new VarExpr("v")));

        PrgState prg10=new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex10,1
        );
        IRepo repo10=new Repo(prg10,"log1.txt");
        Controller cont10=new Controller(repo10,1);

        IStmt ex11=new CompStmt(new CompStmt(new AssignStmt("v",new ConstExpr(10)),
                new CompStmt(new New("a",new ConstExpr(22)),new ForkStmt(
                        new CompStmt(new CompStmt(new WH("a",new ConstExpr(30)),
                                new AssignStmt("v",new ConstExpr(32))),
                                new CompStmt(new PrintStmt(new VarExpr("v")),new PrintStmt(
                                        new RH("a")
                                ))
                                )
                ))),new CompStmt(new PrintStmt(new VarExpr("v")),new PrintStmt(new RH("a"))));
        PrgState prg11=new PrgState(new MyStack<IStmt>(),
                new MyDictionary<String,Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer, FileInfo>(),
                new MyHeap<Integer, Integer>(),
                ex11,1
        );
        IRepo repo11=new Repo(prg11,"log1.txt");
        Controller cont11=new Controller(repo11,1);

        TextMenu menu=new TextMenu();
        menu.addCommand(new ExitCommand("0","exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),cont1));
        menu.addCommand(new RunExample("2",ex2.toString(),cont2));
        menu.addCommand(new RunExample("3",ex3.toString(),cont3));
        menu.addCommand(new RunExample("4",ex4.toString(),cont4));
        menu.addCommand(new RunExample("5",ex5.toString(),cont5));
        menu.addCommand(new RunExample("6",ex6.toString(),cont6));
        menu.addCommand(new RunExample("7",ex7.toString(),cont7));
        menu.addCommand(new RunExample("8",ex8.toString(),cont8));
        menu.addCommand(new RunExample("9",ex9.toString(),cont9));
        menu.addCommand(new RunExample("10",ex10.toString(),cont10));
        menu.addCommand(new RunExample("11",ex11.toString(),cont11));
        menu.show();
    }
}
