package calculator.domain.expression;

import calculator.domain.delimiter.Delimiter;

import java.util.Optional;

public class CustomDelimiterHeader {

    private final String value;

    public CustomDelimiterHeader(final String value) {
        this.value = value;
    }

    // 커스텀 구분자 헤더를 분리하는 것과, 그 속에서 커스텀 구분자를 찾는 건 별도의 책임으로 바라봄
    // 커스텀 구분자가 1개만 존재한다는 제약사항이 없었으므로, 이런 구조로 확장에 유리하게 설계함.
    public Optional<Delimiter> extractDelimiter() {
        if (value.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new Delimiter(value));
    }
}

