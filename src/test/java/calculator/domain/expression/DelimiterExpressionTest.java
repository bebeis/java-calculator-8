package calculator.domain.expression;

import calculator.domain.delimiter.Delimiter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DelimiterExpressionTest {

    @DisplayName("커스텀 구분자가 존재하면 CustomDelimiterHeader를 생성한다.")
    @Test
    void parse_withCustomDelimiter() {
        // given & when
        DelimiterExpression expression = DelimiterExpression.from("//;\\n1;2:3");

        // then
        Optional<Delimiter> delimiterOpt = expression.toHeader().extractDelimiter();
        assertThat(delimiterOpt.get()).isEqualTo(new Delimiter(";"));
    }

    @DisplayName("정규식 특수문자의 경우에도 정상적으로 파싱한다.")
    @Test
    void parse_withSpecialRegexCharacter() {
        // given & when
        DelimiterExpression expression = DelimiterExpression.from("//.\\n1.2:3");

        // then
        Optional<Delimiter> delimiterOpt = expression.toHeader().extractDelimiter();
        assertThat(delimiterOpt.get()).isEqualTo(new Delimiter("."));
    }

    @DisplayName("커스텀 구분자가 존재하지 않으면 빈 헤더를 생성한다.")
    @Test
    void parse_withoutCustomDelimiter() {
        // given & when
        DelimiterExpression expression = DelimiterExpression.from("1,2:3");

        // then
        Optional<Delimiter> delimiterOpt = expression.toHeader().extractDelimiter();
        assertThat(delimiterOpt.isPresent()).isFalse();
    }

    @DisplayName("suffix가 존재하지 않는 경우 예외를 발생시킨다.")
    @Test
    void parse_noSuffix_throwsException() {
        assertThatThrownBy(() -> DelimiterExpression.from("//a1,2,3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("suffix가 잘못된 경우 예외를 발생시킨다.")
    @Test
    void parse_illegalSuffix_throwsException() {
        assertThatThrownBy(() -> DelimiterExpression.from("//a\1,2,3"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}

