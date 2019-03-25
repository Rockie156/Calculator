package Visitors;

import Expressions.Expression;
import Expressions.Operand;
import Expressions.Operator;

/**
 * Returns the tree as an ASCII tree
 * @author Elpis
 *
 */
public class TreeVisitor implements Visitor {
	
	public String visit(Expression e) {
		return getTree(e);
	}
	
	public String getTree(Expression e) {
		return getTree(e, "");
	}
	
	public String getTree(Expression e, String neighbor) {
		if (e instanceof Operand) {
			return e.getValue() + "   " + neighbor;
		}
		Operator operator = (Operator) e;
		String output = "";

		Expression left = operator.getLeftChild();
		Expression right = operator.getRightchild();
		
		// First line
		output += operator.getValue();
		output += "   ";
		output += neighbor;
		
		output += "\n";
		
		// Second line | \
		output += "| \\\n";
		
		output += getTree(left, right.getValue());
//		System.out.println(left.getValue() + " " + right.getValue());
//		System.out.println("Output is: " + output);
		return output;
	}

	@Override
	public String visit(Operand o) {
		return o.getValue();
	}

	@Override
	public String visit(Operator e) {
		return getTree(e);
	}
}
