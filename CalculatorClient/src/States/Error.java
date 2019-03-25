package States;

import Expressions.Expression;

public class Error extends State{
	public Error(State parent) {
		super(parent);
		if (tokens.size() > 1) {
			// special DISCARD behavior
			tokens.remove(tokens.size()-1);
		}
		putKeys("0123456789/*-+=", () -> parent);
	}

	@Override
	public Expression[] changeTree(Expression[] expressions) {
		return expressions;
	}

//	@Override
	public void setExpressions() {
		// We probably want to discard or something here
		// TODO: is this right?®
		discard();
		return;
	}

}
