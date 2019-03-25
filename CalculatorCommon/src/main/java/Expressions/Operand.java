package Expressions;

import Visitors.Visitor;

public class Operand extends Expression {
	private static final long serialVersionUID = -4844116817234824430L;

	public Operand(String value) {
		super(value);
	}


	@Override
	public String accept(Visitor v) {
		return v.visit(this);
		
	}
}
