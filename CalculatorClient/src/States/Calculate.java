package States;

import Expressions.Expression;
import Expressions.Operator;

public class Calculate extends State{
	public Calculate(State parent) {
		super(parent);
		putKeys("0123456789", () -> new GettingFirstOperand(this));
		putKeys("+-*/=", () -> new Start());
		parent.tokens.clear();
	}

	@Override
	public Expression[] changeTree(Expression[] expressions) {
		Operator operator = (Operator) expressions[1];
		operator.setChildren(expressions[0], expressions[2]);
		expressions[0] = operator;
		expressions[1] = null;
		expressions[2] = null;
		return expressions;
	}

//	@Override
	public void setExpressions() {
		operator.setChildren(operand1, operand2);
		operand1 = operator;
		operator = null;
		operand2 = null;
	}
}
