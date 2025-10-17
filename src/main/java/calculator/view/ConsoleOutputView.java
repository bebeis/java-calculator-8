package calculator.view;

import calculator.domain.PositiveNumber;

public class ConsoleOutputView {

    public void printInitMessage() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
    }

    public void printResult(final PositiveNumber number) {
        System.out.println("결과 : " + number);
    }
}
