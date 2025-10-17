package calculator.domain.number;

public class PositiveNumber {
    private final double number;

    public PositiveNumber(final double number) {
        validatePositive(number);
        this.number = number;
    }

    public PositiveNumber(final long number) {
        this((double) number);
    }

    private void validatePositive(final double number) {
        if (number <= 0) {
            throw new IllegalArgumentException("양수만 입력할 수 있습니다");
        }
    }

    public PositiveNumber addTo(final PositiveNumber sum) {
        return new PositiveNumber(this.number + sum.number);
    }

    @Override
    public String toString() {
        if (number == (long) number) {
            return Long.toString((long) number);
        }
        return Double.toString(number);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PositiveNumber)) return false;
        return Math.abs(this.number - ((PositiveNumber) obj).number) < 1e-9;
    }
}
