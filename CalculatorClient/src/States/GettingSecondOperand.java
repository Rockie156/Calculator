package States;

import Expressions.Expression;
import Expressions.Operand;

public class GettingSecondOperand extends State {
    public GettingSecondOperand(State parent) {
        super(parent);
        putKeys("0123456789", () -> new GettingSecondOperand(this));
        putKeys("=", () -> new Calculate(this));
        putKeys("/*-+", () -> new WaitingForNextOperand(this));
    }

    @Override
    public Expression[] changeTree(Expression[] expressions) {
        if (expressions[2] == null) {
            expressions[2] = new Operand(getToken());
        } else {
            expressions[2].appendVal(getToken());
        }
        return expressions;
    }

    @Override
    public void setExpressions() {
        if (operand2 == null) {
            operand2 = new Operand(getToken());
        } else {
            operand2.appendVal(getToken());
        }
    }

}
