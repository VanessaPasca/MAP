package model;

import exceptions.ExpressionException;

public class ArithmExpr extends Exp {
    private Exp op1;
    private Exp op2;
    private char operator;

    public ArithmExpr(Exp op1, Exp op2, char operator) {
        this.op1 = op1;
        this.op2 = op2;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return ""+op1+" "+operator+" "+op2;
    }

    @Override
    int eval(MyIDictionary<String, Integer> table) throws ExpressionException {
        // Evaluate the two expressions in the arithmetic expression
        int v1 = op1.eval(table);
        int v2 = op2.eval(table);
        // Check the operator and do the corresponding operations
        switch (operator) {
            case '+':
                return v1 + v2;
            case '-':
                return v1 - v2;
            case '*':
                return v1 * v2;
            case '/': {
                if (v2 != 0) {
                    return v2 / v1;
                } else {
                    throw new ExpressionException("Division by 0! ");
                }
            }
            default: {
                throw new ExpressionException("Unknown operand!");
            }

        }
    }

}
