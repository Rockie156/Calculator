import States.Calculate;
import States.Error;
import States.Start;
import States.State;
import Visitors.CalcVisitor;
import network.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorInputHandler implements ActionListener {
    public State current;

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

        // Move to the next state
        current = current.getNext(action);

        // Special case -- discard
        if (isError)
            current.discard();

        // Update our operands and operator
        current.setExpressions();

        // Update the calculator display
        updateDisplay();
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
            c.sendToServer(current.operand1);
        }

        if (current.operand1 != null) {
            display_text += current.operand1.accept(new CalcVisitor());
        }

        if (current.operator != null) {
            display_text += " " + current.operator.value;
        }

        if (current.operand2 != null) {
            display_text += " " + current.operand2.accept(new CalcVisitor());
        }

        display.setText(display_text);
    }
}
