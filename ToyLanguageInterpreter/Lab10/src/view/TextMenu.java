package view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    // Each command has a string key ( number ) and a description
    private Map<String, Command> commands;
    public TextMenu(){
        commands=new HashMap<>();
    }

    public void addCommand(Command c){
        commands.put(c.getKey(),c);
    }

    private void printMenu(){
        for (Command comm: commands.values()){
            String line=String.format("%4s -> %s",comm.getKey(),comm.getDescription());
            System.out.println(line);
        }
    }

    public void show(){
        Scanner sc=new Scanner(System.in);
        while (true){
            printMenu();
            System.out.println("Input your option");
            String key=sc.nextLine();
            Command com=commands.get(key);
            if (com==null){
                System.out.println("Invalid Option");
                continue;
            }
            com.execute();
        }
    }
}
