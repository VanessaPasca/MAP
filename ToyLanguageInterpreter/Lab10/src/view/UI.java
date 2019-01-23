package view;

import controller.Controller;
import exceptions.InputException;
import model.*;
import utils.*;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Create: x=7; y=x+8; print(x); print(y)
    - doua comp stmt formate dintr-un comp stmt
    x=7; y=10; if (x>y) then z=x else z=y; print(z)

 */
public class UI {
    private Controller cont;

    public UI(Controller cont) {
        this.cont = cont;
    }

    public void run(){
        Set<String> options= Stream.of("0","1","2").collect(Collectors.toSet());
        Scanner in = new Scanner(System.in);
        String option = "-1";
        while (option!="0"){
            StringBuffer buff=new StringBuffer();
            buff.append("Main Menu: \n");
            buff.append("\t 0. exit \n");
            buff.append("\t 1. Input program \n");
            buff.append("\t 2. Evaluate program \n");
            buff.append("Input option:");
            System.out.println(buff.toString());
            option=in.next();
            while (!options.contains(option)){
                System.out.println("Please input a valid option! \n");
                System.out.println("Input option:");
                option=in.next();
            }
            if (option.equals("1")){
                inputProgramMenu(in);
            }else
            if (option.equals("2")){
                evaluateProgramMenu(in);
            }else
            if (option.equals("0")){
                System.out.println("Bye!");
                break;
            }

        }
    }

    public void inputProgramMenu(Scanner in){
        Set<String> options= Stream.of("0","1","2").collect(Collectors.toSet());
        String option;
        System.out.println("Evaluate Menu: ");
        System.out.println("\t 0. exit ");
        System.out.println("\t 1. input existing ");
        System.out.println("\t 2. input new ");
        System.out.println("Input your option: ");
        option=in.next();
        while (!options.contains(option)){
            System.out.println("Please input a valid option! ");
            System.out.println("Input option:");
            option=in.next();
        }
        if (option.equals("0")){
            System.out.println("Exit");
            return;
        }else if (option.equals("1")){
            inputExisting(in);
        }else {
            cont.addProg(new PrgState(
                    new MyStack<IStmt>(),
                    new MyDictionary<String, Integer>(),
                    new MyList<Integer>(),
                    new MyFileTable<Integer,FileInfo>(),
                    new MyHeap<Integer, Integer>(),
                    createStatement(in),1
            ));
        }
        }


    public void inputExisting(Scanner in) {
        Set<String> options= Stream.of("0","1","2","3","4","5").collect(Collectors.toSet());
        String option;
        System.out.println("Existing program: ");
        System.out.println("\t 0. exit ");
        System.out.println("\t 1.  v=2;Print(v); ");
        System.out.println("\t 2.  a=2+3*5;b=a+1;Print(b); ");
        System.out.println("\t 3. a=2-2;(If a Then v=2 Else v=3);Print(v) ; ");
        System.out.println("\t 4. file example 1");
        System.out.println("\t 5. file example 2");
        System.out.println("\t 6.file example 3");
        System.out.println("Please input an option! \n");
        IStmt ex1= new CompStmt(new AssignStmt("v",new ConstExpr(2)), new PrintStmt(new
                VarExpr("v")));
        IStmt ex2 = new CompStmt(new AssignStmt("a", new ArithmExpr(new ConstExpr(2),new
                ArithmExpr(new ConstExpr(3), new ConstExpr(5),'*'),'+')),
                new CompStmt(new AssignStmt("b",new ArithmExpr(new VarExpr("a"), new
                        ConstExpr(1),'+')), new PrintStmt(new VarExpr("b"))));
        IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithmExpr(new ConstExpr(2), new
                ConstExpr(2),'-')),
                new CompStmt(new IfStmt(new VarExpr("a"),new AssignStmt("v",new ConstExpr(2)), new
                        AssignStmt("v", new ConstExpr(3))), new PrintStmt(new VarExpr("v"))));
        IStmt ex4=new CompStmt(new CompStmt(new OpenRFile("var_f","test.in"),new CompStmt(new ReadFile(new VarExpr("var_f"),"var_c"),new PrintStmt(new VarExpr("var_c")))),
                new CompStmt(new IfStmt(new BoolExpr(new VarExpr("var_c"),new ConstExpr(1),">"),
                        new CompStmt(new ReadFile(new VarExpr("var_f"),"var_c"),new PrintStmt(new VarExpr("var_c"))),new PrintStmt(new ConstExpr(0))),

                new CloseRFile(new VarExpr("var_f"))));

        IStmt ex5=new CompStmt(new CompStmt(new OpenRFile("var_f","test.in"),new CompStmt(new ReadFile(new ArithmExpr(new VarExpr("var_f"),new ConstExpr(2),'+'),"var_c"),new PrintStmt(new VarExpr("var_c")))),
                new CompStmt(new IfStmt(new BoolExpr(new VarExpr("var_c"),new ConstExpr(1),">"),
                        new CompStmt(new ReadFile(new VarExpr("var_f"),"var_c"),new PrintStmt(new VarExpr("var_c"))),new PrintStmt(new ConstExpr(0))),

                        new CloseRFile(new VarExpr("var_f"))));

        IStmt ex6=new CompStmt(new OpenRFile("f","test.in"),new OpenRFile("g","test.in"));
        System.out.println("Input your option: ");
        option=in.next();
        IStmt chosen;
        while (!options.contains(option)){
            System.out.println("Please input a valid option! ");
            System.out.println("Input option:");
            option=in.next();
        }
        if (option.equals("0")){
            System.out.println("Exit");
            return;
    }else if (option.equals("1")){
            chosen=ex1;
        }else if (option.equals("2")){
            chosen=ex2;
        }else if (option.equals("3")){
            chosen=ex3;
        }else  if (option.equals("4")){
            chosen=ex4;
        }else{
            chosen=ex6;
        }
        cont.addProg(new PrgState(
                new MyStack<IStmt>(),
                new MyDictionary<String, Integer>(),
                new MyList<Integer>(),
                new MyFileTable<Integer,FileInfo>(),
                new MyHeap<Integer, Integer>(),
                chosen,
                1
        ));
    }

    public Exp createExpr(Scanner in){
        Set<String> options= Stream.of("0","1","2","3","4").collect(Collectors.toSet());
        String option;
        System.out.println("Expresion: ");
        System.out.println("\t 0. exit ");
        System.out.println("\t 1.  Constant ");
        System.out.println("\t 2.  Arithmetic ");
        System.out.println("\t 3. Variable ");
        System.out.println("\t 4. Boolean ");
        System.out.println("Input your option:");
        option=in.next();
        while (!options.contains(option)){
            System.out.println("Please input a valid option! ");
            System.out.println("Input option:");
            option=in.next();
        }
        if (option.equals("0"))
        {
            System.out.println("Exit.");
            return null;
        }
        else if (option.equals("1"))
        {
            System.out.print("Enter number for constant expression -> ");
            if (!in.hasNextInt())
                throw new InputException(in.next()+"is not a number!");
            return new ConstExpr(in.nextInt());
        }
        else if (option.equals("2"))
        {
            System.out.print("Enter operator -> ");
            char oper=in.next().charAt(0);
            return new ArithmExpr(createExpr(in), createExpr(in),oper);
        }
        else if (option.equals("3"))
        {
            System.out.print("Enter name for variable -> ");
            String name = in.next();
            String let[] = name.split("");
            try { Integer.parseInt(let[0]); throw new InputException("Not a string!"); }
            catch(NumberFormatException e) { }
            return new VarExpr(name);
        } else {
            System.out.print("Enter comparator -> ");
            String comp=in.next();
            return new BoolExpr(createExpr(in),createExpr(in),comp);
        }

    }
    public IStmt createStatement(Scanner in){
        Set<String> options= Stream.of("0","1","2","3","4").collect(Collectors.toSet());
        String option;
        System.out.println("Create Statement: ");
        System.out.println("\t 0. exit \n");
        System.out.println("\t 1.  assign ");
        System.out.println("\t 2.  comp ");
        System.out.println("\t 3. print ");
        System.out.println("\t 4. IF ");
        System.out.println("Input your option:");
        option=in.next();
        while (!options.contains(option)){
            System.out.println("Please input a valid option! \n");
            System.out.println("Input option:");
            option=in.next();
        }
        if (option.equals("0"))
        {
            System.out.println("Exit.");
            return null;
        }
        else if (option.equals("1"))
        {
            System.out.print("Enter name for variable -> ");
            String name = in.next();
            String let[] = name.split("");
            try { Integer.parseInt(let[0]); throw new InputException(name+" not string!"); }
            catch(NumberFormatException e) { }
            return new AssignStmt(name, createExpr(in));
        }
        else if (option.equals("2"))
        {
            return new CompStmt(createStatement(in), createStatement(in));
        }
        else if (option.equals("3"))
        {
            return new PrintStmt(createExpr(in));
        }
        else if (option.equals("4"))
        {
            return new IfStmt(createExpr(in), createStatement(in), createStatement(in));
        }
        return null;

    }

    public void evaluateProgramMenu(Scanner in){
        Set<String> options= Stream.of("0","1","2").collect(Collectors.toSet());
        String option;
        System.out.println("Create Statement: ");
        System.out.println("\t 0. exit ");
        System.out.println("\t 1.  one step ");
        System.out.println("\t 2.  entire ");

        System.out.println("Input your option:");
        option=in.next();
        while (!options.contains(option)){
            System.out.println("Please input a valid option! ");
            System.out.println("Input option:");
            option=in.next();
        }
        if (option.equals("0"))
        {
            System.out.println("Exit.");
           return;
        }
        else if (option.equals("1"))
        {
           // cont.oneStep();
        }
        else if (option.equals("2"))
        {
            cont.completeEvaluation();
        }
    }


}
