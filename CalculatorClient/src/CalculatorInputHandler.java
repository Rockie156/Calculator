import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import Expressions.Expression;
import States.*;
import States.Error;
import Visitors.CalcVisitor;
import network.Client;

public class CalculatorInputHandler implements ActionListener {
	public State current;

	public Expression operand1 = null;
	public Expression operand2 = null;
	public Expression operator = null;
	
	public CalculatorInputWindow calculator;
	
	CalculatorInputHandler() {
		current = new Start(null);
	}
	
	public void setCalculator(CalculatorInputWindow calculator) {
		this.calculator = calculator;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Action is the input to the transition function
		Character action = e.getActionCommand().charAt(0);
		// Keep track if the past state was an error state
		boolean isError = current instanceof Error;
		// System.out.print(current.tokens + " ");
		
		// Move to the next state
		current = current.getNext(action);
		
		// Special case -- discard
		if (isError)
			current.discard();
		
		// Update our operands (trees)
		changeTree();
		
		// Update the calculator display
		updateDisplay();
		
		// Special case -- reset
		if (current instanceof Calculate)
			reset();
	}
	
	/**
	 * Updates the operands and operator based on
	 * the current state's rules
	 */
	public void changeTree() {
		Expression [] expressions = { operand1, operator, operand2 };
		expressions = current.changeTree(expressions);
		operand1 = expressions[0];
		operator = expressions[1];
		operand2 = expressions[2];
	}
		
	/**
	 * Reset our calculator handler states
	 */
	public void reset() {
		operand1 = null;
		operator = null;
		operand2 = null;
	}
	
	/**
	 * Updates the display
	 */
	public void updateDisplay() {
		JTextField display = calculator.getDisplay();
		String display_text = "";
		
		if (current instanceof Error) {
			display.setText("Err");
			return;
		}
		
		if (current instanceof Calculate) {
			Client c = new Client();
			c.sendToServer(operand1);
		}
		
		if (operand1 != null) {
			display_text += operand1.accept(new CalcVisitor());
		}
		
		if (operator != null) {
			display_text += " " + operator.value;
		}
		
		if (operand2 != null) {
			display_text += " " + operand2.accept(new CalcVisitor());
		}
		
		display.setText(display_text);
	}
}
