package calculator.domain.delimiter;

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
            throw new IllegalArgumentException("구분자는 공백일 수 없습니다");
        }
    }

    private void validateNotAllDigits(final String value) {
        // TODO: 정규식으로 개선하기
        if (value.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("구분자가 모두 숫자로 구성될 수 없습니다.");
        }
    }

    private void validateNoControlCharacterExcludeTab(final String value) {
        // TODO: 정규식으로 개선하기
        for (char c : value.toCharArray()) {
            if (Character.isISOControl(c) && c != '\t') {
                throw new IllegalArgumentException("문자열에 제어문자가 포함되어 있습니다.");
            }
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
}
