package Visitors;

import Expressions.Expression;
import Expressions.Operand;
import Expressions.Operator;

public interface Visitor {
	String visit(Expression e);
	String visit(Operand o);
	String visit(Operator o);
}
