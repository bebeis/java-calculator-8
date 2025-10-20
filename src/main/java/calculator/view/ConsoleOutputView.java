package calculator.view;

public class ConsoleOutputView {

    public void printInitMessage() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
    }

    public void printResult(final String result) {
        System.out.println("결과 : " + result);
    }
}
