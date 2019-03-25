package States;

import Expressions.Expression;
import Expressions.Operator;

public class WaitingForNextOperand extends State{
	public WaitingForNextOperand(State parent) {
		super(parent);
		putKeys("0123456789", () -> new GettingSecondOperand(this));
		putKeys("/*-+=", () -> new Error(this));
	}

	@Override
	public Expression[] changeTree(Expression[] expressions) {
		// If we are given an operator, merge operand 1 and 2 if necessary
		if (expressions[2] != null) {
			Operator operator = (Operator) expressions[1];
			operator.setChildren(expressions[0], expressions[2]);
			expressions[0] = operator;
			expressions[2] = null;
		}
		// Update the operator
		expressions[1] = new Operator(getToken());
		return expressions;
	}

//	@Override
	public void setExpressions() {
		// If we are given an operator, merge operand 1 and 2 if necessary
		if (operand2 != null) {
			operator.setChildren(operand1, operand2);
			operand1 = operator;
			operand2 = null;
			operator = null;
		}
		// Update the operator
		operator = new Operator(getToken());
	}
}
