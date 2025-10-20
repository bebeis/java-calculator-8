package calculator.controller;

import calculator.service.CalculatorService;
import calculator.view.ConsoleInputView;
import calculator.view.ConsoleOutputView;

public class CalculatorController {
    private final ConsoleOutputView consoleOutputView = new ConsoleOutputView();
    private final ConsoleInputView consoleInputView = new ConsoleInputView();
    private final CalculatorService calculatorService = new CalculatorService();

    public void run() {
        consoleOutputView.printInitMessage();
        String expr = consoleInputView.readExpression();

        String result = calculatorService.calculate(expr);
        consoleOutputView.printResult(result);
    }
}
