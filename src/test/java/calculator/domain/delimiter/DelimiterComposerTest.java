package calculator.domain.delimiter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DelimiterComposerTest {

    @DisplayName("구분자 목록을 변환하여 정규 표현식을 얻는다")
    @Test
    void delimitersToRegex() {
        // given
        DelimiterComposer composer = new DelimiterComposer();
        Delimiter delimiterA = new Delimiter("a");
        Delimiter delimiterB = new Delimiter("b");
        composer.addDelimiter(delimiterA);
        composer.addDelimiter(delimiterB);

        // when
        String regex = composer.toRegexForSplit();

        // then
        assertThat(regex).isEqualTo("\\Qa\\E|\\Qb\\E");
    }

    @DisplayName("구분자가 존재하지 않는 경우 빈 문자열을 얻는다.")
    @Test
    void getEmptyRegexStringByEmptyDelimiters() {
        // given
        DelimiterComposer composer = new DelimiterComposer();

        // when
        String regex = composer.toRegexForSplit();

        // then
        assertThat(regex.isEmpty()).isTrue();
    }
}