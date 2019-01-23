package view;

import controller.Controller;

public class RunExample extends Command {
    private Controller cont;

    public RunExample(String key, String description, Controller cont) {

        super(key, description);
        this.cont = cont;
    }

    @Override
    public void execute() {
        cont.completeEvaluation();
    }
}
