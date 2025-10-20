package calculator.domain.expression;

import calculator.domain.operand.Operand;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class OperandSection {

    private final String expr;

    public OperandSection(final String expr) {
        this.expr = expr;
    }

    public List<Operand> extractWith(final String delimiterRegex) {
        String[] tokens = expr.split(delimiterRegex, -1);

        return Arrays.stream(tokens)
                .map(String::trim)
                .peek(this::validateToken)
                .map(this::toOperand)
                .toList();
    }

    private void validateToken(final String token) {
        if (token.isEmpty()) {
            throw new IllegalArgumentException("연속된 구분자는 허용되지 않습니다.");
        }
    }

    private Operand toOperand(String token) {
        if (!Pattern.matches("\\d+(\\.\\d+)?", token)) {
            throw new IllegalArgumentException("유효하지 않은 숫자 형식입니다: " + token);
        }

        return new Operand(Double.parseDouble(token));
    }
}

