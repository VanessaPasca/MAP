package view;

import controller.Controller;
import repository.IRepo;
import repository.Repo;

public class Main {
    public static void main(String[] args) {
        IRepo repo=new Repo();
        Controller cont=new Controller(repo,1);
        UI ui=new UI(cont);
        ui.run();
    }
}
