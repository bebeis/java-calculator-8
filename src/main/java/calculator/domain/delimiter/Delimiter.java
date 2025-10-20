package calculator.domain.delimiter;

import java.util.Objects;
import java.util.regex.Pattern;

public class Delimiter {
    private static final int MAX_LENGTH = 30;

    private final String value;

    public Delimiter(final String value) {
        validatePossibleValue(value);
        this.value = value;
    }

    private void validatePossibleValue(final String value) {
        validateNotAllDigits(value);
        validateNotEmpty(value);
        validateNoControlCharacterExcludeTab(value);
        validateDelimiterLength(value);
    }

    private void validateNotEmpty(final String value) {
        if (value.isEmpty()) {
            throw new IllegalArgumentException("구분자는 비어있을 수 없습니다");
        }
    }

    private void validateNotAllDigits(final String value) {
        if (value.matches("^\\d+$")) {
            throw new IllegalArgumentException("구분자가 모두 숫자로 구성될 수 없습니다.");
        }
    }


    private void validateNoControlCharacterExcludeTab(final String value) {
        for (char c : value.toCharArray()) {
            validateNoControlCharacterExceptTab(c);
        }
    }

    private void validateNoControlCharacterExceptTab(final char c) {
        if (Character.isISOControl(c) && c != '\t') {
            throw new IllegalArgumentException("문자열에 제어문자가 포함되어 있습니다.");
        }
    }

    private void validateDelimiterLength(final String value) {
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("구분자가 너무 깁니다. 30자 이내로 작성해주세요");
        }
    }

    public String toRegexToken() {
        return Pattern.quote(value);
    }

    @Override
    public boolean equals(final Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Delimiter delimiter = (Delimiter) o;
        return Objects.equals(value, delimiter.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
