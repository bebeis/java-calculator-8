package calculator.domain.operand;

public class Operand {
    private final double number;

    public Operand(final double number) {
        validatePositive(number);
        this.number = number;
    }

    public Operand(final long number) {
        this((double) number);
    }

    private void validatePositive(final double number) {
        if (number <= 0) {
            throw new IllegalArgumentException("양수만 입력할 수 있습니다");
        }
    }

    public Operand addTo(final Operand sum) {
        return new Operand(this.number + sum.number);
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
        if (!(obj instanceof Operand)) return false;
        return Math.abs(this.number - ((Operand) obj).number) < 1e-9;
    }
}
