package Visitors;

import Expressions.Expression;
import Expressions.Operand;
import Expressions.Operator;

/**
 * Returns the value of a given tree as a String
 * Because the visit() pattern is abstracted for
 * both calculate and makeTree
 * @author Elpis
 *
 */
public class CalcVisitor implements Visitor {
	
	public String visit(Expression e) {
		if (e instanceof Operand)
			return visit((Operand) e);
		return visit((Operator) e);
	}

	@Override
	public String visit(Operand o) {
		return o.getValue();
	}

	@Override
	public String visit(Operator operator) {
		Expression left = operator.getLeftChild();
		Expression right = operator.getRightchild();
		
		Float leftValue = Float.parseFloat(visit(left));
		Float rightValue = Float.parseFloat(visit(right));
		
		Float returnValue = null;
		switch (operator.getValue()) {
			case "+":
				returnValue = leftValue + rightValue;
				break;
			case "-":
				returnValue = leftValue - rightValue;
				break;
			case "/":
				if (rightValue == 0)  {
					returnValue = Float.NaN;
					break;
				}
				returnValue = leftValue / rightValue;
				break;
			case "*":
				returnValue = leftValue * rightValue;
				break;
		}
		// Edge case where 
		if (returnValue == null)
			return operator.getValue();
		return returnValue.toString();
	}
		
}
