package main;

import controller.Controller;
import repository.IRepo;
import repository.Repo;
import view.UI;

public class Main {
    public static void main(String[] args) {
        IRepo repo=new Repo("loguri.txt");
        Controller cont=new Controller(repo,1);
        UI ui=new UI(cont);
        ui.run();
    }
}
