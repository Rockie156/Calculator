package Expressions;

import Visitors.Visitor;

import java.io.Serializable;

public abstract class Expression implements Serializable {
	private static final long serialVersionUID = 8865010430507387491L;
	public String value;
	
	public Expression(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public void appendVal(String val) {
		this.value += val;
	}
	
	public abstract String accept(Visitor v);
}
