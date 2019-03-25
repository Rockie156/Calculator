public class ClientDriver {
    public static void main(String[] args) {
        CalculatorInputWindow calculator = new CalculatorInputWindow();
        CalculatorInputHandler handler = new CalculatorInputHandler();
        calculator.attach(handler);
        handler.setCalculator(calculator);
    }
}
