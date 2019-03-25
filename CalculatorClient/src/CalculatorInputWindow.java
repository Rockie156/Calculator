import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculatorInputWindow extends JFrame {
    private static final long serialVersionUID = -7817746093836654542L;

    JTextField numDisplay;
    JButton[] numbers;
    JButton zero;
    JButton add;
    JButton subtract;
    JButton multiply;
    JButton divide;
    JButton percent;
    JButton equals;
    JButton clear;
    JButton left;
    JButton right;

    GridBagLayout x;

    /**
     * Constructor for the calculator input window
     * Heavily inspired from https://codereview.stackexchange.com/questions/97430
     * It's just UI so I hope it's okay that it was copied. It's sort of pretty.
     */
    public CalculatorInputWindow() {
        super("Calculator");
        setLayout(new GridBagLayout());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        GridBagConstraints gbc = new GridBagConstraints();

        numDisplay = new JTextField();
        numDisplay.setPreferredSize(new Dimension(130, 30));
        numDisplay.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(numDisplay, gbc);

        zero = new JButton("0");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        add(zero, gbc);

        numbers = new JButton[9];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int seq = i * 3 + j;
                numbers[seq] = new JButton(Integer.toString(seq + 1));
                gbc.gridx = j + 1;
                gbc.gridy = 4 - i;
                gbc.gridwidth = 1;
                add(numbers[seq], gbc);
            }
        }

        add = new JButton("+");
        gbc.gridx = 4;
        gbc.gridy = 2;
        add(add, gbc);

        subtract = new JButton("-");
        gbc.gridx = 4;
        gbc.gridy = 3;
        add(subtract, gbc);

        multiply = new JButton("*");
        gbc.gridx = 4;
        gbc.gridy = 4;
        add(multiply, gbc);

        divide = new JButton("/");
        gbc.gridx = 4;
        gbc.gridy = 5;
        add(divide, gbc);

        equals = new JButton("=");
        gbc.gridx = 2;
        gbc.gridy = 5;
        //gbc.gridheight = 3;
        add(equals, gbc);

        clear = new JButton("C");
        gbc.gridx = 3;
        gbc.gridy = 5;
        //gbc.gridheight = 1;
        add(clear, gbc);

        left = new JButton("(");
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        //add(left, gbc);

        right = new JButton(")");
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        //add(right, gbc);

        pack();
    }

    public void attach(ActionListener listener) {
        JButton[] buttons = {zero, add, subtract, multiply, divide, equals, clear, left, right};
        for (JButton button : buttons) {
            button.addActionListener(listener);
        }
        for (JButton button : numbers) {
            button.addActionListener(listener);
        }
    }

    public JTextField getDisplay() {
        return numDisplay;
    }
}
