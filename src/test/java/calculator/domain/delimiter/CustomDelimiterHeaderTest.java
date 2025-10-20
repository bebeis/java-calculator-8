package calculator.domain.delimiter;

import calculator.domain.expression.CustomDelimiterHeader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class CustomDelimiterHeaderTest {

    @DisplayName("헤더 값이 존재하면 Delimiter를 추출한다.")
    @Test
    void extractDelimiter_whenHeaderExists() {
        // given
        CustomDelimiterHeader header = new CustomDelimiterHeader(";");

        // when
        Optional<Delimiter> delimiterOpt = header.extractDelimiter();

        // then
        assertThat(delimiterOpt.isPresent()).isTrue();
        assertThat(delimiterOpt.get()).isEqualTo(new Delimiter(";"));
    }

    @DisplayName("헤더 값이 비어있으면 빈 Optional을 반환한다.")
    @Test
    void extractDelimiter_whenHeaderEmpty() {
        // given
        CustomDelimiterHeader header = new CustomDelimiterHeader("");

        // when
        Optional<Delimiter> delimiterOpt = header.extractDelimiter();

        // then
        assertThat(delimiterOpt.isPresent()).isFalse();
    }

    @DisplayName("여러 문자로 구성된 구분자도 추출할 수 있다.")
    @Test
    void extractDelimiter_multiCharacterDelimiter() {
        // given
        CustomDelimiterHeader header = new CustomDelimiterHeader("a2;c");

        // when
        Optional<Delimiter> delimiterOpt = header.extractDelimiter();

        // then
        assertThat(delimiterOpt.isPresent()).isTrue();
        assertThat(delimiterOpt.get()).isEqualTo(new Delimiter("a2;c"));
    }
}

