package calculator.domain;

public class PositiveNumber {

    private final Number number;

    public PositiveNumber(Number number) {
        validatePositive(number);
        this.number = number;
    }

    public void validatePositive(Number number) {
        if (number.doubleValue() <= 0) {
            throw new IllegalArgumentException("양수만 입력할 수 있습니다");
        }
    }

    @Override
    public String toString() {
        return number.toString();
    }
}
