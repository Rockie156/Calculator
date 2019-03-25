package Expressions;

import Visitors.Visitor;

public class Operator extends Expression {
	private static final long serialVersionUID = -79543191319045450L;
	public Expression left;
	public Expression right;
	
	public Operator(String value) {
		super(value);
	}

	@Override
	public String accept(Visitor v) {
		return v.visit(this);
	}
	
	public void setChildren(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	public Expression getLeftChild() {
		return left;
	}
	
	public Expression getRightchild() {
		return right;
	}
}
