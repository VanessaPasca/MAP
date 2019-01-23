package model;

public class Numbers extends Exp {
    int val;

    public Numbers(int val) {
        this.val = val;
    }

    @Override
    int eval(MyIDictionary<String, Integer> table) {
        return 0;
    }
}
