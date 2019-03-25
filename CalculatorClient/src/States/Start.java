package States;

import Expressions.Expression;

public class Start extends State {
    public Start(State parent) {
        super(parent);
        putKeys("0123456789", () -> new GettingFirstOperand(this));
        putKeys("+-*/=", () -> new Start(null));
    }

    public Start() {
        this(null);
    }

    @Override
    public Expression[] changeTree(Expression[] expressions) {
        for (int i = 0; i < expressions.length; i++) {
            expressions[i] = null;
        }
        return expressions;
    }

    @Override
    public void setExpressions() {
        operand1 = null;
        operator = null;
        operand2 = null;
    }
}
