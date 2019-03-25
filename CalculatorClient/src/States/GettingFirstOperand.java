package States;

import Expressions.Expression;
import Expressions.Operand;

public class GettingFirstOperand extends State{
	public GettingFirstOperand(State parent) {
		super(parent);
		putKeys("0123456789", () -> new GettingFirstOperand(this));
		putKeys("=", () -> new Error(this));
		putKeys("/*-+", () -> new WaitingForNextOperand(this));
	}

	@Override
	public Expression[] changeTree(Expression [] expressions) {
		if (expressions[0] == null) {
			expressions[0] = new Operand(getToken());
		} else {
			expressions[0].appendVal(getToken());
		}
		return expressions;
	}

//	@Override
	public void setExpressions() {
		if (operand1 == null) {
			operand1 = new Operand(getToken());
		} else {
			operand1.appendVal(getToken());
		}
	}
}
